angular.module('MainApp')
.controller('ProfileController',['AuthenticationService','$rootScope','$http',function(AuthenticationService,$rootScope,$http){
    var self=this;
    var init=function(){
        self.user={};
        $rootScope.globals.companyName="Employee Portal";
        var adjUsername=$rootScope.globals.currentUser.username.replace(new RegExp('\\.', 'g'), '@');
        $http.get($rootScope.api+'profile/'+adjUsername).then(function success(response){
            self.user=response.data;
            self.user.name=self.user.firstName+' '+self.user.lastName;
            AuthenticationService.setCredentials({
                name:self.user.name,
                username:self.user.username
            });
        },
        function error(response){
            console.log('Error in fetching user profile: '+response.status+' '+response.statusText);
        });
    };
    init();
}]);
