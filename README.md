<h1 align="center" style="text-align:center;">
🎬

Movies

</h1><br>

<h4 align="center" style="text-align:center;">
I know what you are thinking, "OH NO! Another movies app using TMDB API in Android...WHYYYYYYYY????". And I have one answer only to you. 

Why not, after all, when life gives you lemonade, make lemons. And life will be like what?!!

<br>
<br>

<img src="https://github.com/thisaislan/just-images/raw/main/images/movies/phil_dunphy.png">

</h4>

<h5 align="center" style="text-align:center;">

`No, I am kidding there is a real reason!`😝

</h5>


<h1 align="center" style="text-align:center;"></h1>

<br>
<br>

## Table of contents

- [So... why?](#so-why)
- [The project itself](#the-project-itself)
- [Whats and whys (WW)](#whats-and-whys-ww)
- [Next steps](#next-steps)
- [Conclusion (so far)](#conclusion-so-far)

<br>


## So... why?

Like any developer, from time to time I need to create something as a way to test some technologies and use some approach that my day job can't provide me. In this case, nothing better than a well-known project to help me do this and be able to judge the result easily.

> ##### I said that, I had a good reason!

<br>

## The project itself

Well, nothing new here, TMDB API, Android, MVVM, repository, you know, basic things!
The main idea is to build a small application and expand it over time. The initial commit will contain only the base project to serve as backbone of the project and ensure future scalability with new features and technologies.
In some sense, the project will be my playground to test some crazy ideas 😎!

Now let's see some images of the project:

<h1 align="center" style="text-align:center;">
<img style="max-height:500;" src="https://github.com/thisaislan/just-images/raw/main/images/movies/screen_recorder_main_screen_portrait.gif">

</h1>

<h5>
  <table>
   <thead>
    <tr>
     <th> <img style="max-height:500px;"src="https://github.com/thisaislan/just-images/raw/main/images/movies/screenshot_main_screen_portrait.png"> </th>
     <th> <img style="max-width:500px;"src="https://github.com/thisaislan/just-images/raw/main/images/movies/screenshot_main_screen_landscape.png"> </th>
    </tr>
   </thead>
  <tbody>
    <tr>
     <td> <img style="max-height:500;" src="https://github.com/thisaislan/just-images/raw/main/images/movies/screenshot_details_screen_portrait.png"> </td>
     <td> <img style="max-width:500px;" src="https://github.com/thisaislan/just-images/raw/main/images/movies/screenshot_details_screen_landscape.png"> </td>
    </tr>
  </tbody>
  </table>
</h5>

<h5 align="center" style="text-align:center;">

I will update that section in every necessary update!

> I almost forgot this, to run the project generate API key from [The Movie Database API](https://developers.themoviedb.org/3), place the key in 'local.properties' file as apiKey="<YOUR_API_KEY_HERE">.

</h5>

<br>

## Whats and Whys (WW)

To me this is the best section in every project, tell a little more of every decision that we made as a software engineer is so exciting, so challenging and every now or than I find out that I made some mistakes, but that is part of the job.

The first value of any project of mine is to democratize access and configuration of the project, that means the project must be as easy and understandable as possible beyond the programmers. This has impact in the project, so we should think with some carefully in some details.

Perhaps the most important idea of work in a project like this is to create something that allows the entire team to express themselves, artists, designers, programmers at any levels. Because of this I am inclined to think we need do two things: write simple, easy-to-read code and use as much of the main tool potential as possible to enable interdisciplinary collaboration. Well, this approach has some challenges, discussions and, of course, people who don't like it, which is perfect, there's nothing like a good discussion to help evolve any area 🙂.

Now that you know a little more about the way I think (and why I will refactor some sections of this projects), let's dive in what and why I sue in this project:

<br>

`MVVM` - I don't think I need to expand on this topic. Separation of concerns, improved testability, lifecycle awareness, stateFlow support. And more, but may most important MVVM is recommended by Google as part of their official Android Jetpack libraries. The ViewModel component is designed with MVVM in mind, providing built-in support and tools

`Compose` - That thing solved so many things that I disliked in the android development...so perfect...😭. Drastically reduces boilerplate code, declarative approach, allowing you to describe what your UI should look like based on the current state. You simply define the UI for different states, and Compose handles the updates automatically, eliminating the need to manually manage UI updates, is built from the ground up for Kotlin, taking full advantage of Kotlin’s language features, even if you’re not ready to fully migrate your project to Compose, it’s designed to work seamlessly with existing Views and XML layouts...two words...per...fection...

`Repository` - Provides a clean separation between the data layer and the rest of the application. I know sometimes is complicate to see the line of responsibility of the repository and others components, but believe me, using this can help us a lot, in topics like test, decoupling data sources and scalability and maintainability.

`StateFlow` - To be fair, part of it is personal preference, but StateFlow is part of Kotlin’s Coroutines API, providing a more consistent and unified approach for managing and handling state in your application. Since many Android apps already rely on coroutines, StateFlow fits naturally into the existing coroutine framework. Unlike LiveData, StateFlow is not lifecycle-aware by default. While this might seem like a drawback, it actually simplifies the logic in some scenarios, especially when your code doesn’t need to be aware of lifecycle changes. For lifecycle-aware behavior, you can easily integrate StateFlow with Jetpack Compose or use collectAsStateWithLifecycle(). 

`Hilt` - First, Hilt is officially supported by Google and is built on top of Dagger, a well-established DI framework. It’s part of the Android Jetpack libraries, ensuring long-term support and regular updates in line with other Android components. Also provides compile-time checking for dependency injection. This means you catch errors related to DI at compile-time rather than at runtime, which leads to safer and more predictable code. Koin, on the other hand, resolves dependencies at runtime, which might introduce potential issues only visible when the app is running.
> Even thought the impact in the performance itself is irrelevant most of the time, that is something always haunts my mind...👻

`Glide` - Ah... Although I heard glide is better than Picasso if you are working in a chat app, and has support to gif, the real reason was I wanted to use GlideCompose...

`Retrofit` - I'm used to using it, I didn't even think about it...lol

`Moshi` - Moshi is a powerful and efficient JSON library that is optimized for Kotlin development. Its native Kotlin support, performance advantages, and flexibility make it an excellent choice for Android projects, particularly those that leverage Kotlin’s language features to the fullest...(I just copied this, to be real same answer of Retrofit...😆)

`More things` - I tried to put some cool stuff in there along the way, some because I've never used it, some because they're cool. Among these things I can say, `Paging`, because I needed it, and when it works well it really works. `Dynamic Theme`, `chroma icon`, `splashscreen`, I am not biiiiiiiig fan of splash screens, but I like the way we can work in the new sdks. Some cool Google tools like, `Google fonts`, `Material theme builder`.
I must confess that in some cases I simply put things in there because I want to work with them (I'm human too... 😅), but I try to use them in a cool way and always think about the next steps.
Two points that are important to say are, I avoid using alpha versions in general, so this might answer some questions (betas included). And I wanted to use Android Studio Koala, for the "I want" reason. 🐨

<br>

## Next steps

This is an evolving project, things will naturally change over time, but right now there are a lot of things I want to do right now. For example:

`First of all add tests` - Typical developer thinking high and procrastinating the basics...

`I want more animations` - Compose is beautiful, so I want to use more of this power. I putted some animations, but I need more...

`Stop use the network entities on the whole code` - ... no comments...(forgive me, I justed want release the base app as soon as possible...)

`Use the new Compose navigation` - Okay, here I must say that the new compose navigation (beta now) is much better than the previous one. Although I have some concerns about navigation in Android apps (which I will discuss more in some future section), I really think that compose navigation will be the standard for future navigation systems in mobile apps. To me the problem came when we think in use multiple activities (my personal choice to divide flows) and keep everything simple and "followable". (More later in a commit near of you...😜)

> I am using activities today because I think in do a better usage of these flows in the future. Probably every activity will have its own internal navigation.

`Improve the usage of viewmodels events and states` - I tried be a little purist here, and use only events and states on my viewmodels, at some points some things caught my attention. For example composables handle very well `Flow<PagingData<T>>` (well to much maybe, lol), but the state of one screen that depends of that flow should db change by the viewmodel, so should I ignore the excellent composable work and force the usage (sometimes almost unsafe) of the viewmodel, or break the rule and use `collectAsStateWithLifecycle` and handle the state inside of the composable itself!!?

> Here we can have a huge architecture discursion

`Better usage of the API services` - Well, here actually we have  a lot of things. Better usage of the API means use better the total information (by the way, we are getting more than what is in use today). That means the app will grow, that means more features and screens, that means I will divide into submodules (why not), that means (probably) I will revisit the current flows, that means a lot of changes...

<br>

## Conclusion (so far)

The first and obvious conclusion is that Android development is a looooooot of fun!! It always has been and I hope it always will be. Although that project is still a little part of the whole idea, I can see why we can continue use this kind of app as a template to show and evaluate skills (I personally recreate this app at least 5 times, and I am not talking about challenges I saw as evaluator in companies).

But I honestly think that the only way we can evolve is through exchanges, exposing the project itself cannot help me see any possible improvements and evolve my skills. Other than that, I know it can help other developers see a way how this implementation can be done, but it cannot help new developers to create analytical thinking about the projects, architecture, engineering and others (because of that I love the [WW](#whats-and-whys-ww) section).

With that said, feel free to reach me with questions, idea and maybe pull requests, keep in mind I have some ideas for the project, but I will love discuss new approach with other person (developer or not developer 😉)!

<br>
<br>

<h1 align="center" style="text-align:center;"></h1>

<br>
<br>


<h4 align="center" style="text-align:center;">

  Thank you!
  
  :heart:

</h4>