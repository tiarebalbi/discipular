angular.module 'app', ['ngRoute', 'app.controller']

.config ['$routeProvider', ($routeProvider) ->
  $routeProvider
  .when '/membro', {
    controller: 'HomeCtrl',
    templateUrl: '/app/components/lider/membro/index.html'
  }
  .otherwise {
    redirectTo: '/'
  }


]

