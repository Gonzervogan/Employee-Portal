angular.module('MainApp')
.factory('AuthenticationService',['$rootScope','$http','$cookieStore',function($rootScope,$http,$cookieStore){
    var service={};
    service.login=function(username,password,callback){
        var user={};
        user.username=username;
        user.password=password;
        $http.post($rootScope.api+'authenticate',user).then(function success(response){
            var ret={};
            ret.success=response.data;
            if(response.data){
                service.setCredentials({username:username});
            }else{
                ret.message='Username or password is incorrect';
            }
            callback(ret);
        },function error(response){
            console.log('Unable to authenticate: '+response.status+' '+response.statusText);
        });
    };
    service.setCredentials=function(user){
        $rootScope.globals.currentUser=user;
        $cookieStore.put('globals',$rootScope.globals);
    };
    service.logout=function(callback){
        var adjUsername=$rootScope.globals.currentUser.username.replace(new RegExp('\\.', 'g'), '@');
        $http.get($rootScope.api+'logout/'+adjUsername).then(function success(response){
            if(response.data){
                service.clearCredentials();
                callback();
            }else
                console.log('Unable to logout!');
        },function error(response){
            console.log('Unable to logout: '+response.status+' '+response.statusText);
        });
    };
    service.clearCredentials=function(){
        $rootScope.globals={};
        $cookieStore.remove('globals');
    };
    return service;
}]);
