(function(){
    var app = angular.module('statistics', [ ]);

    app.controller('statsController', ['$http', '$scope', function($http, $scope){

        //zmienne testowe
        var address = "http://localhost:5000/stats/";
        //var address = "http://bochen.eu-central-1.elasticbeanstalk.com/stats/";

        this.wordNumber;
        this.sentencesNumber;

        var wordsNumberContext = this;
        var sentencesNumberContext = this;

        $http.get(address + '/getSentencesNumber').then(successCallbackSent, errorCallbackSent);
        function successCallbackSent(response){
            sentencesNumberContext.sentencesNumber = response.data;
        }
        function errorCallbackSent(error){
            alert('Błąd! Komunikacja z bazą danych zakończona błędem.');
        }

        $http.get(address + 'getWordsNumber').then(successCallbackWord, errorCallbackWord);
        function successCallbackWord(response){
            wordsNumberContext.wordNumber = response.data;
        }
        function errorCallbackWord(error){
            alert('Błąd! Komunikacja z bazą danych zakończona błędem.');
        }


    }]);


})();