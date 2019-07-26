angular.module('MainApp').controller('DetailsController', ['$http', '$rootScope', function($http, $rootScope) {
    var self = this;
    self.company = {};
    self.company.activeUsers = [];
    var currentUser = $rootScope.globals.currentUser.username.replace(new RegExp('\\.','g'), '@');
    var init = function() {

        /*$http.get($rootScope.api+'company/'+currentUser).then(function success(response){
            response.data.pageViews=response.data.pageViews.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
            self.company=response.data;
            $rootScope.globals.companyName=response.data.name;
        },
        function error(response){
            console.log('Error in fetching Company Details: '+response.status+' '+response.statusText);
        });*/

        //Mimicking REST CALL
        self.company = {
            "id": 3,
            "name": "Reebok",
            "address": ["Boston, Massachusetts", "USA", "457569"],
            "logo": "reebok.svg",
            "pageViews": "1,413,476",
            "activeUsers": ["Ed Chase", "Jason Bourne", "Peter Griffin", "Bart Simpson", "Lois Griffin", "Michael Scott", "Pam Beasley", "Raj Koothrappali", "Bernadette Rostenkowski"]
        };
        $rootScope.globals.companyName = self.company.name;
    };
    init();
}
]);
