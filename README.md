# Social Jukebox Application in JavaFX with MVC Pattern and Observer Pattern
## Music Player App with client server function to upvote Songs and playlists to change the order of Songs.

### Prerequisites: Java11 , Maven, mp3 files in the working directory

### Steps to start the app:
 1. build the executable:
 ```
 mvn clean package 
 ```
 
2. Start the app with gui(needs to have mp3 files in the root folder):

```
target/appassembler/bin/gseRadio --gui 
```

### Additional features:

#### Starts a  server on requested port streaming music:

```
target/appassembler/bin/gseRadio --server --streaming=<port number> 
```
#### Send request to server to get teh following informations:
current song:
```
http://<IP>:<PORT>/current-song
```
current playlist:
```
http://<IP>:<PORT>/playlist
```
### Starts a client to input ip and port to connect to the streaming server:

```
target/appassembler/bin/gseRadio --client
```
