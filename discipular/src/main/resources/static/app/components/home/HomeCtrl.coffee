angular.module 'app.controller'
.controller 'HomeCtrl', ['$scope', '$routeParams', ($scope) ->
  $scope.model = false;

  $scope.fecharModel = ->
    $scope.model = false

  $scope.abrirModel = ->
    $scope.model = true

]