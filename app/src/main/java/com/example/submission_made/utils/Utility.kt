package com.example.submission_made.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import com.example.submission_made.data.pojo.MovieData

object Utility {

    fun populateMoviesData(dataList: ArrayList<MovieData>) {

        // Data 1
        var movieData = MovieData()
        movieData.id = 384018
        movieData.title = "Fast & Furious Presents: Hobbs & Shaw"
        movieData.overview = "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw."
        movieData.poster_path = "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"
        movieData.backdrop_path = "/hpgda6P9GutvdkDX5MUJ92QG9aj.jpg"
        movieData.vote_count = 581
        movieData.vote_average = 6.5
        movieData.popularity = 456.135
        movieData.release_date = "2019-08-01"
        dataList.add(movieData)

        // Data 2
        movieData = MovieData()
        movieData.id = 420818
        movieData.title = "The Lion King"
        movieData.overview = "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his."
        movieData.poster_path = "/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg"
        movieData.backdrop_path = "/1TUg5pO1VZ4B0Q1amk3OlXvlpXV.jpg"
        movieData.vote_count = 1739
        movieData.vote_average = 7.1
        movieData.popularity = 265.913
        movieData.release_date = "2019-07-12"
        dataList.add(movieData)

        // Data 3
        movieData = MovieData()
        movieData.id = 420818
        movieData.title = "Detective Conan: The Fist of Blue Sapphire"
        movieData.overview = "23rd movie in the \"Detective Conan\" franchise."
        movieData.poster_path = "/86Y6qM8zTn3PFVfCm9J98Ph7JEB.jpg"
        movieData.backdrop_path = "/wf6VDSi4aFCZfuD8sX8JAKLfJ5m.jpg"
        movieData.vote_count = 41
        movieData.vote_average = 5.1
        movieData.popularity = 172.051
        movieData.release_date = "2019-04-12"
        dataList.add(movieData)

        // Data 4
        movieData = MovieData()
        movieData.id = 420818
        movieData.title = "Spider-Man: Far from Home"
        movieData.overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent."
        movieData.poster_path = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg"
        movieData.backdrop_path = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg"
        movieData.vote_count = 2972
        movieData.vote_average = 7.8
        movieData.popularity = 168.668
        movieData.release_date = "2019-06-28"
        dataList.add(movieData)

        // Data 5
        movieData = MovieData()
        movieData.id = 466272
        movieData.title = "Once Upon a Time in Hollywood"
        movieData.overview = "A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles."
        movieData.poster_path = "/8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg"
        movieData.backdrop_path = "/b82nVVhYNRgtsTFV1jkdDwe6LJZ.jpg"
        movieData.vote_count = 273
        movieData.vote_average = 7.5
        movieData.popularity = 158.833
        movieData.release_date = "2019-07-25"
        dataList.add(movieData)

        // Data 6
        movieData = MovieData()
        movieData.id = 299534
        movieData.title = "Avengers: Endgame"
        movieData.overview = "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store."
        movieData.poster_path = "/or06FN3Dka5tukK1e9sl16pB3iy.jpg"
        movieData.backdrop_path = "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg"
        movieData.vote_count = 8683
        movieData.vote_average = 8.4
        movieData.popularity = 150.001
        movieData.release_date = "2019-04-24"
        dataList.add(movieData)

        // Data 7
        movieData = MovieData()
        movieData.id = 531309
        movieData.title = "Brightburn"
        movieData.overview = "What if a child from another world crash-landed on Earth, but instead of becoming a hero to mankind, he proved to be something far more sinister?"
        movieData.poster_path = "/sJWwkYc9ajwnPRSkqj8Aue5JbKz.jpg"
        movieData.backdrop_path = "/uHEI6v8ApQusjbaRZ8o7WcLYeWb.jpg"
        movieData.vote_count = 540
        movieData.vote_average = 5.7
        movieData.popularity = 125.613
        movieData.release_date = "2019-05-09"
        dataList.add(movieData)

        // Data 8
        movieData = MovieData()
        movieData.id = 506574
        movieData.title = "Descendants 3"
        movieData.overview = "The teenagers of Disney's most infamous villains return to the Isle of the Lost to recruit a new batch of villainous offspring to join them at Auradon Prep."
        movieData.poster_path = "/7IRy0iHdaS0JI3ng4ZYlk5gLSFn.jpg"
        movieData.backdrop_path = "/gzdxeOiO76hDoOSKYCxegj7bk4T.jpg"
        movieData.vote_count = 140
        movieData.vote_average = 8.2
        movieData.popularity = 118.616
        movieData.release_date = "2019-08-09"
        dataList.add(movieData)

        // Data 9
        movieData = MovieData()
        movieData.id = 458156
        movieData.title = "John Wick: Chapter 3 – Parabellum"
        movieData.overview = "Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn."
        movieData.poster_path = "/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg"
        movieData.backdrop_path = "/vVpEOvdxVBP2aV166j5Xlvb5Cdc.jpg"
        movieData.vote_count = 1845
        movieData.vote_average = 7.1
        movieData.popularity = 102.57
        movieData.release_date = "2019-05-15"
        dataList.add(movieData)

        // Data 10
        movieData = MovieData()
        movieData.id = 449562
        movieData.title = "The Hustle"
        movieData.overview = "Two female scam artists, one low rent and the other high class, compete to swindle a naïve tech prodigy out of his fortune. A remake of the 1988 comedy \"Dirty Rotten Scoundrels.\""
        movieData.poster_path = "/hHAD7cx1j2RAyjwREVgJeLcLVoi.jpg"
        movieData.backdrop_path = "/s6awXOxTKYQLSktiIJfI3969dZH.jpg"
        movieData.vote_count = 376
        movieData.vote_average = 6.1
        movieData.popularity = 98.324
        movieData.release_date = "2019-05-09"
        dataList.add(movieData)
    }

    fun populateTvShowData(dataList: ArrayList<MovieData>) {

        // Data 1
        var movieData = MovieData()
        movieData.id = 384018
        movieData.title = "Fast & Furious Presents: Hobbs & Shaw"
        movieData.overview = "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw."
        movieData.poster_path = "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"
        movieData.backdrop_path = "/hpgda6P9GutvdkDX5MUJ92QG9aj.jpg"
        movieData.vote_count = 581
        movieData.vote_average = 6.5
        movieData.popularity = 456.135
        movieData.release_date = "2019-08-01"
        dataList.add(movieData)

        // Data 2
        movieData = MovieData()
        movieData.id = 420818
        movieData.title = "The Lion King"
        movieData.overview = "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his."
        movieData.poster_path = "/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg"
        movieData.backdrop_path = "/1TUg5pO1VZ4B0Q1amk3OlXvlpXV.jpg"
        movieData.vote_count = 1739
        movieData.vote_average = 7.1
        movieData.popularity = 265.913
        movieData.release_date = "2019-07-12"
        dataList.add(movieData)

        // Data 3
        movieData = MovieData()
        movieData.id = 420818
        movieData.title = "Detective Conan: The Fist of Blue Sapphire"
        movieData.overview = "23rd movie in the \"Detective Conan\" franchise."
        movieData.poster_path = "/86Y6qM8zTn3PFVfCm9J98Ph7JEB.jpg"
        movieData.backdrop_path = "/wf6VDSi4aFCZfuD8sX8JAKLfJ5m.jpg"
        movieData.vote_count = 41
        movieData.vote_average = 5.1
        movieData.popularity = 172.051
        movieData.release_date = "2019-04-12"
        dataList.add(movieData)

        // Data 4
        movieData = MovieData()
        movieData.id = 420818
        movieData.title = "Spider-Man: Far from Home"
        movieData.overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent."
        movieData.poster_path = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg"
        movieData.backdrop_path = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg"
        movieData.vote_count = 2972
        movieData.vote_average = 7.8
        movieData.popularity = 168.668
        movieData.release_date = "2019-06-28"
        dataList.add(movieData)

        // Data 5
        movieData = MovieData()
        movieData.id = 466272
        movieData.title = "Once Upon a Time in Hollywood"
        movieData.overview = "A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles."
        movieData.poster_path = "/8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg"
        movieData.backdrop_path = "/b82nVVhYNRgtsTFV1jkdDwe6LJZ.jpg"
        movieData.vote_count = 273
        movieData.vote_average = 7.5
        movieData.popularity = 158.833
        movieData.release_date = "2019-07-25"
        dataList.add(movieData)

        // Data 6
        movieData = MovieData()
        movieData.id = 299534
        movieData.title = "Avengers: Endgame"
        movieData.overview = "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store."
        movieData.poster_path = "/or06FN3Dka5tukK1e9sl16pB3iy.jpg"
        movieData.backdrop_path = "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg"
        movieData.vote_count = 8683
        movieData.vote_average = 8.4
        movieData.popularity = 150.001
        movieData.release_date = "2019-04-24"
        dataList.add(movieData)

        // Data 7
        movieData = MovieData()
        movieData.id = 531309
        movieData.title = "Brightburn"
        movieData.overview = "What if a child from another world crash-landed on Earth, but instead of becoming a hero to mankind, he proved to be something far more sinister?"
        movieData.poster_path = "/sJWwkYc9ajwnPRSkqj8Aue5JbKz.jpg"
        movieData.backdrop_path = "/uHEI6v8ApQusjbaRZ8o7WcLYeWb.jpg"
        movieData.vote_count = 540
        movieData.vote_average = 5.7
        movieData.popularity = 125.613
        movieData.release_date = "2019-05-09"
        dataList.add(movieData)

        // Data 8
        movieData = MovieData()
        movieData.id = 506574
        movieData.title = "Descendants 3"
        movieData.overview = "The teenagers of Disney's most infamous villains return to the Isle of the Lost to recruit a new batch of villainous offspring to join them at Auradon Prep."
        movieData.poster_path = "/7IRy0iHdaS0JI3ng4ZYlk5gLSFn.jpg"
        movieData.backdrop_path = "/gzdxeOiO76hDoOSKYCxegj7bk4T.jpg"
        movieData.vote_count = 140
        movieData.vote_average = 8.2
        movieData.popularity = 118.616
        movieData.release_date = "2019-08-09"
        dataList.add(movieData)

        // Data 9
        movieData = MovieData()
        movieData.id = 458156
        movieData.title = "John Wick: Chapter 3 – Parabellum"
        movieData.overview = "Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn."
        movieData.poster_path = "/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg"
        movieData.backdrop_path = "/vVpEOvdxVBP2aV166j5Xlvb5Cdc.jpg"
        movieData.vote_count = 1845
        movieData.vote_average = 7.1
        movieData.popularity = 102.57
        movieData.release_date = "2019-05-15"
        dataList.add(movieData)

        // Data 10
        movieData = MovieData()
        movieData.id = 449562
        movieData.title = "The Hustle"
        movieData.overview = "Two female scam artists, one low rent and the other high class, compete to swindle a naïve tech prodigy out of his fortune. A remake of the 1988 comedy \"Dirty Rotten Scoundrels.\""
        movieData.poster_path = "/hHAD7cx1j2RAyjwREVgJeLcLVoi.jpg"
        movieData.backdrop_path = "/s6awXOxTKYQLSktiIJfI3969dZH.jpg"
        movieData.vote_count = 376
        movieData.vote_average = 6.1
        movieData.popularity = 98.324
        movieData.release_date = "2019-05-09"
        dataList.add(movieData)
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun isAppInstalled(uri: String, context: Context): Boolean {
        val pm = context.packageManager
        var installed = false
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            installed = true
        } catch (e: PackageManager.NameNotFoundException) {
            installed = false
        }

        return installed
    }

    fun viewUrl(url: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun watchYoutubeVideo(id: String, context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (isAppInstalled("com.google.android.youtube", context)) {
                intent.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity")
            }
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=$id")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }
}