Game Authoring Environment 
======

### Architecture
* MVC-style, but instead of separating model from view, controller separates entity creator vs. editor
    * Entities are created in `EntityCreator`, and then the controller transfers the object to the `EntityEditor`
* Controller also keeps track of the whole global state
    * Global holds list of levels
    * Level holds list of entities
    * Entity holds list of components

![Does this](./GAE_architecture.JPG)
