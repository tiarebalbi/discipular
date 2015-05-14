angular.module 'app.service'
.service 'MembroService', (Restangular) ->
  Restangular.all('membros')
