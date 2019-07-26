angular.module('MainApp')
.controller('DetailsController',['$http','$rootScope',function($http,$rootScope){
    var self=this;
    self.company={};
    self.company.activeUsers=[];
    var currentUser=$rootScope.globals.currentUser.username.replace(new RegExp('\\.', 'g'), '@');
    var init=function(){
        $http.get($rootScope.api+'company/'+currentUser).then(function success(response){
            response.data.pageViews=response.data.pageViews.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
            self.company=response.data;
            $rootScope.globals.companyName=response.data.name;
        },
        function error(response){
            console.log('Error in fetching Company Details: '+response.status+' '+response.statusText);
        });
    };
    init();
}]);
