(function(){
    var app = angular.module('vocabularyBuilder', [ ]);

    //Zmienne testowe
    //var address = "http://localhost:5000/demo/";
    var address = "http://bochen.eu-central-1.elasticbeanstalk.com/demo/";

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();

    if(dd<10) {
        dd = '0'+dd
    }

    if(mm<10) {
        mm = '0'+mm
    }

    today = yyyy + '-' + mm + '-' + dd;

    app.controller('wordsController', ['$http', '$scope', function($http, $scope){

        //funkcja dodająca nowe słowo jeśli nie było takiego wcześniej
        this.addWord = function(engWord, translation) {
            //zmienna do weryfikacji czy dodawane słowo istniało wcześniej
            var wordsCounter = 0;
            //pętla iterująca po wszystkich słowach - jeśli nie znajdzie identycznego słowa to umożliwi dodanie nowego
            for(var i = 0; i < this.word.length; i++) {
                if(this.word[i].engForm === engWord) {
                    var wordsCounter = 1;
                    alert('Wyraz już istnieje w słowniku!');
                    break;
                }
            }
            if(wordsCounter === 0){
                this.word.push({
                    engForm: engWord,
                    plForm: translation,
                    sentences:[{
                        asentence: {id: 0, acontent: '', corr: 'null'},
                        qsentence: {id: 1, qcontent: '', corr: 'null'},
                        nsentence: {id: 2, ncontent: '', corr: 'null'}
                    }
                    ],
                    synonyms: {syncontent: '', corr: 'null'},
                    addDate: today,
                    modDate: today,
                    maxId: 2
                });

                //Wywołanie metody dodającej nowe słowa
                $http({
                    url: address + "addNewWord",
                    method: "GET",
                    params: {word: engWord, translation: translation, addDate: today, modDate: today}
                }).then(successCallback, errorCallback);
                function successCallback(response){
                    console.log("Zapis nowego słowa przebiegł poprawnie");
                }
                function errorCallback(error){
                    alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
                }

            }
        };

        //funkcja usuwająca słowa
        this.removeWord = function(engWord) {
            //pętla iterująca po wszystkich słowach - jeśli znajdzie identyczne słowo, usuwa je z tablicy
            for(var i = 0; i < this.word.length; i++) {
                if(this.word[i].engForm === engWord) {
                    this.word.splice(i, 1);

                    //Wywołanie metody usuwającej słowo
                    $http({
                        url: address + "deleteWord",
                        method: "GET",
                        params: {word: engWord}
                    }).then(successCallback, errorCallback);
                    function successCallback(response){
                        console.log("Usunięcie słowa z przyległościami przebiegło poprawnie");
                    }
                    function errorCallback(error){
                        alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
                    }
                    break;
                }
            }
        };

        //funkcja dodająca nowe zdania do wybranego słowa jeśli wszystkie dotychczasowe są uzupełnione
        this.addSentences = function(engWord) {
            //zmienna do weryfikacji czy istnieją jakieś nieuzupełnione zdania
            var emptySentenceCounter = 0;
            //pętla iterująca po wszystkich słowach - jak znajdzie wybrane slowo i nie będzie pustych zdań to dodaje 3 nowe, puste zdania
            for(var i = 0; i < this.word.length; i++) {
                if(this.word[i].engForm === engWord) {
                    for(var j = 0; j < this.word[i].sentences.length; j++) {
                        //Jeśli są puste zdania to wyświetl alert, dodaj counter i wyjdź z pętli
                        if(this.word[i].sentences[j].asentence.acontent === '' || this.word[i].sentences[j].qsentence.qcontent === '' || this.word[i].sentences[j].nsentence.ncontent === ''){
                            alert('Uzupełnij puste zdania.');
                            emptySentenceCounter++;
                            break;
                        }
                    }
                    //Jeśli nie ma pustych zdań to dodaj 3 nowe
                    if(emptySentenceCounter === 0){
                        this.word[i].sentences.push({
                            asentence: {id: this.word[i].maxId+1, acontent: '', corr: 'null'},
                            qsentence: {id: this.word[i].maxId+2, qcontent: '', corr: 'null'},
                            nsentence: {id: this.word[i].maxId+3, ncontent: '', corr: 'null'}
                        });
                        this.word[i].maxId = this.word[i].maxId+3;

                        console.log(this.word[i].maxId+3)

                        //Funkcja dodające 3 nowe zdania po stronie serwera
                        $http({
                            url: address + "addNewPackage",
                            method: "GET",
                            params: {word: engWord}
                        }).then(successCallback, errorCallback);
                        function successCallback(response){
                            console.log("Dodanie nowej paczki zdań przebiegło poprawnie");
                        }
                        function errorCallback(error){
                            alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
                        }
                        break;
                    }
                }
            }
        };

        //Funkcja do ustawienia poprawności zdań
        this.updateCorrectnessValue = function(corrValue, engWord, sequenceID){
            for(var i = 0; i < this.word.length; i++) {
                if(this.word[i].engForm === engWord) {
                    for(var j = 0; j < this.word[i].sentences.length; j++) {
                        if(this.word[i].sentences[j].asentence.id === sequenceID){
                            this.word[i].sentences[j].asentence.corr = corrValue;
                        }
                        if(this.word[i].sentences[j].qsentence.id === sequenceID){
                            this.word[i].sentences[j].qsentence.corr = corrValue;
                        }
                        if(this.word[i].sentences[j].nsentence.id === sequenceID){
                            this.word[i].sentences[j].nsentence.corr = corrValue;
                        }
                    }
                    //Funkcja aktualizująca poprawność wybranego zdania
                    $http({
                        url: address + "updateSentenceCorrectness",
                        method: "GET",
                        params: {word: engWord, sentCorr: corrValue, sentId: sequenceID}
                    }).then(successCallback, errorCallback);
                    function successCallback(response){
                        console.log("Aktualizacja poprawności zdania przebiegła poprawnie");
                    }
                    function errorCallback(error){
                        alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
                    }
                    break;
                }
            }
        };

        //Funkcja do aktualizacji treści zdań
        this.updateSentenceContentValue = function(contentValue, engWord, sequenceID){
            for(var i = 0; i < this.word.length; i++) {
                if(this.word[i].engForm === engWord) {
                    console.log("contentValue: " + contentValue + "<--");
                    for(var j = 0; j < this.word[i].sentences.length; j++) {
                        if(this.word[i].sentences[j].asentence.id === sequenceID){
                            this.word[i].sentences[j].asentence.acontent = contentValue;
                        }
                        if(this.word[i].sentences[j].qsentence.id === sequenceID){
                            this.word[i].sentences[j].qsentence.qcontent = contentValue;
                        }
                        if(this.word[i].sentences[j].nsentence.id === sequenceID){
                            this.word[i].sentences[j].nsentence.ncontent = contentValue;
                        }
                    }

                    this.word[i].modDate = today;

                    //Funkcja aktualizująca treść wybranego zdania
                    $http({
                        url: address + "updateSentenceContent",
                        method: "GET",
                        params: {word: engWord, sent_cont: contentValue, sent_id: sequenceID, mod_date: today}
                    }).then(successCallback, errorCallback);
                    function successCallback(response){
                        console.log("Aktualizacja treści zdania przebiegła poprawnie");
                    }
                    function errorCallback(error){
                        alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
                    }
                    break;
                }
            }
        };

        //Funkcja do aktualizacji treści tłumaczenia
        this.updateSentenceTranslationValue = function(engWord, plWord){
            for(var i = 0; i < this.word.length; i++) {
                if(this.word[i].engForm === engWord) {
                    console.log("engWord: " + engWord + "plWord: " + plWord);
                    this.word[i].plForm = plWord;

                    //Funkcja aktualizująca treść wybranego zdania
                    $http({
                        url: address + "updateTranslation",
                        method: "GET",
                        params: {word: engWord, translation: plWord}
                    }).then(successCallback, errorCallback);
                    function successCallback(response){
                        console.log("Aktualizacja treści translacji przebiegła poprawnie");
                    }
                    function errorCallback(error){
                        alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
                    }
                    break;
                }
            }
        };

        //Funkcja do aktualizacji treści synonimu
        this.updateSynonymContent = function(engWord, synCont){
            for(var i = 0; i < this.word.length; i++) {
                if(this.word[i].engForm === engWord) {
                    console.log("synCont: " + synCont);
                    this.word[i].synonyms.syncontent = synCont;

                    this.word[i].modDate = today;

                    //Funkcja do aktualizacji treści synonimu po stronie serwera
                    $http({
                        url: address + "updateSynonymContent",
                        method: "GET",
                        params: {word: engWord, synCont: synCont, modDate: today}
                    }).then(successCallback, errorCallback);
                    function successCallback(response){
                        console.log("Aktualizacja treści synonimu przebiegła poprawnie");
                    }
                    function errorCallback(error){
                        alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
                    }
                    break;
                }
            }
        };

        //Funkcja do aktualizacji poprawności synonimu
        this.updateSynonymCorrectness = function(engWord, synCorr){
            for(var i = 0; i < this.word.length; i++) {
                if(this.word[i].engForm === engWord) {
                    console.log("synCorr: " + synCorr);
                    this.word[i].synonyms.corr = synCorr;

                    //Funkcja do aktualizacji poprawności synonimu po stronie serwera
                    $http({
                        url: address + "updateSynonymCorrectness",
                        method: "GET",
                        params: {word: engWord, synCorr: synCorr}
                    }).then(successCallback, errorCallback);
                    function successCallback(response){
                        console.log("Aktualizacja poprawności synonimu przebiegła poprawnie");
                    }
                    function errorCallback(error){
                        alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
                    }
                    break;
                }
            }
        };

        var that = this;

        $http.get(address + "sendAllWords").then(successCallback, errorCallback);
        function successCallback(response){
            that.word = response.data;
        }
        function errorCallback(error){
            alert('Błąd! Komunikacja z bazą danych zakończona błędem.');
        }

        this.returnString = function(engWord, sequenceID) {
            return engWord+sequenceID;
        };

        this.removeSpaces = function(engWord) {
            return engWord=engWord.replace(/ +/g, "");
        };

        $scope.findWordParam = null;
        $scope.onlyWrong = 0;
        $scope.onlyOk = 0;
        $scope.notValid = 0;
        $scope.addDateParam = null;
        $scope.modDateParam = null;

        //funkcja obsługująca filtrowanie
        this.search = function() {
            //Wywołanie metody do filtrowania
            console.log("onlyWrong: " + $scope.onlyWrong + " findWord: " + $scope.findWordParam + " onlyOk: " + $scope.onlyOk + " notValid: " + $scope.notValid + "adddate: " + $scope.addDateParam + "moddate: " + $scope.modDateParam);
            $http({
                url: address + "search",
                method: "GET",
                params: {findword: $scope.findWordParam, onlywrong: $scope.onlyWrong, onlyok: $scope.onlyOk, notvalid: $scope.notValid, adddate: $scope.addDateParam, moddate: $scope.modDateParam}
            }).then(successCallback, errorCallback);
            function successCallback(response){
                that.word = response.data;
                console.log("Filtrowanie słów zakończone powodzeniem.");
            }
            function errorCallback(error){
                alert('Błąd! Komunikacja z bazą danych zakończona błędem. ' + error);
            }
        };

        //********** Metody testowe >>
        $scope.showAlert = function(text) {
            alert(text);
        };

        this.alert = function(test) {
            alert(test);
        };
        //<< Metody testowe **********

    }]);

    //Zestaw testowy
    var words = [
        {
            "engForm": 'briliant',
            "plForm": 'cudownie',
            "sentences":[{
                "no":"1",
                "asentence": {"id": "0", "acontent": 'Affirmative sentece 1.1!', "corr": "true"},
                "qsentence": {"id": "1", "qcontent": 'Question sentece 1.1!', "corr": ""},
                "nsentence": {"id": "2", "ncontent": 'Negative sentece 1.1!', "corr": "true"}
            },{
                "no":"2",
                "asentence": {"id": "3", acontent: 'Affirmative sentece 1.2!', corr: "true"},
                "qsentence": {"id": "4", qcontent: 'Question sentece 1.2!', corr: "true"},
                "nsentence": {"id": "5", ncontent: 'Negative sentece 1.2!', corr: ""}
            }
            ],
            "synonyms": {"syncontent": "Example of synonym 1!", "corr": "true"},
            "addDate": "2017-11-07",
            "modDate": "2017-11-15",
            "maxId": "5"
        },
        {
            engForm: 'pleasant',
            plForm: 'miły, uprzejmy, sympatyczny',
            sentences:[{
                "asentence": {"id": "0", "acontent": 'Affirmative sentece 2.1!', "corr": "true"},
                "qsentence": {"id": "1", "qcontent": 'Question sentece 2.1!', "corr": "null"},
                "nsentence": {"id": "2", "ncontent": 'Negative sentece 2.1!', "corr": "true"}
            }
            ],
            "synonyms": {"syncontent": "Example of synonym 1!", "corr": "true"},
            "addDate": "2017-11-07",
            "modDate": "2017-11-15",
            "maxId": "2"
        }
    ];

})();