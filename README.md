# # Applications Guessing Game

Hello from Hiba Bayazid and Mehidine Bachache. This is our application submission for the mobile app development course, which is an application where you guess the application's name based on the picture, you might win or lose points, or not even collect them; it all depends on what level you play!

Features:
* A main page that lets the user choose the level they want to play.
* Fetching the android applications to display from a website.
* Applying regular expressions after fetching the
content of the webpage to extract the URL of the images and
the correct answer.
* Each level displays the logo of the application in question
and underneath it, includes 4 buttons – each one of
them contains a possible answer. Clearly, one of them
should contain the correct answer.


## Levels
* Noob Level:
  * Users will enter the game room where logo of
random android applications are shown on the
screen with 4 different buttons containing 4
different answers (with one correct answer).
* Amateur Level:
  * Same as easy Level but a scoring system will be
implemented. Correct answers will result in a +2
and wrong answers will result in a -1 score.
* Legend Level:
  * All the above in addition to a Timer.



## Important Codes

```java

# connecting and fetching the website
public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.connect();

            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

# regex for getting the image sources
String regex = "http(s?)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./]*)+\\.(?:[gG][iI][fF]|[jJ][pP][gG]|[jJ][pP][eE][gG]|[pP][nN][gG]|[bB][mM][pP])";

# regex for getting the application names
String regex2 = "<h2 class=\"order-last md:order-first font-bold font-brand text-lg md:text-xl leading-normal w-full\">(.*?)</h2>";
```
## Thank You!
We thank you for taking the time and checking our readme, for more details and insights on the design and code, check the full application!
