# BongoTalkies

This app is a simple app which shows movies in a grid view and user can see movie details information by clicking movie preview

[Watch the video](https://drive.google.com/file/d/142Du_6qPI6BZXiIygtkUL_rtMSJtKGYY/view?usp=sharing)

It show cases MVVM architecture with addition domain layer. Layers are
 * data
 * domain
 * view model
 * view

## Layer description

* In data layer I have used repository pattern
* I have used interactor or usecase for each business logic which will only do single task
* Google jetpack library viewmodel is used which is lifecycle aware
* In view there is MainActivity which holds two fragment 
  - MovieListFragment
  - MovieDetailsFragment
  
 ## Library used 
 
 * hilt - dipendency injection
 * retrofit - network call
 * moshi - json deserialization
 * glide - image loading 
 * swiperefreshlayout - for refreshing movie list
 * jetpack libs
 * testing libs
 
 ## testing
  * test cases are written for two classes
    - MovieRepo(repository class) - dependent fake class MovieService is written
    - GetMovies(domain class) - dependent fake class MovieRepo is written
  
