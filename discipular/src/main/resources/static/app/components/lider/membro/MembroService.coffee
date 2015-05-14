angular.module 'app.service'
.service 'MembroService', ['restangular', (Restangular) ->
  Restangular.all('membros')
]