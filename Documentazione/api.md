# API Documentation

## retrieveAllModules
- returns all entities as a List
#### Url
- /api/modules
#### Method
- Get
#### Data Params
- none
## retrieveModuleById
- returns the entity with the specified ID
#### Url
- /api/modules/{id}
#### Method
- Get
#### Data Params
- @PathVariable long id

## deleteModuleById
- delete the entity with the specified ID
### Url
- /api/modules/{id}
### Method
- Delete
### Data Params
- @PathVariable long id

## createModule
- returns a new ResponseEntity<Module\>
### Url
- /api/modules
### Method
- Post
### Data Params
- @RequestBody Module module

## updateModule
- returns an updated ResponseEntity<Module\>, updating the entity with the specified ID
### Url
- /api/modules/{id}
### Method
- Put
### Data Params
- @RequestBody Module module, @PathVariable long id

 <p align="center"><b> There will be an API for filtering, paging, research and generation/download of a PDF report </b> </p>
        <p align="center"> <b>    < --To be completed -- > </p></b>
