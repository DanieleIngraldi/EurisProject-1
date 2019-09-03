# GetMapping 
- get all elements ("/api/module")
- get one element ("/api/module/{id}") (<b>@PathVariable</b> long id)

# DeleteMapping
- delete all elements ("/api/module")
- delete one element ("/api/module/{id}") (<b>@PathVariable</b> long id)

# PostMapping
- create a new element("/api/module") (<b>@RequestBody</b> Module module)

# PutMapping
- update an element ("/api/module/{id}") (<b>@RequestBody</b> Module module, <b>@PathVariable</b> long id)
