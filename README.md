# pLuck
A top of the line event app featuring everything you'd expect and a full theme customizer

## Building
1. Clone the repo
2. Receive a copy of `google-services.json` and copy it into the folder `pLuck/app/`, replacing the file that is already there with the same name. Note: The `google-services.json` must be in place before syncing gradle with android studio.
3. Launch android studio, click `File` -> `Open`, navigate to the repository, navigate into the `pLuck/` directory, and then open `build-gradle.kts`
4. After android studio syncs with gradle, receive a copy of the API, and other info required for Cloudinary, and add the contents into the file `local.properties` in the `pLuck/` folder. Note: The `local.properties` file is only created after gradle syncs with android studio.
5. Finally, build the `app` target

## Wiki
Are wiki can be accessed here: https://github.com/CMPUT301F25vertex/vertex-event-lottery/wiki

## Credits
Made by:
Anant Gupta, Arsh Ahsan, Ahmed Sajid, Ayaad Sharif, Benjamin Bingham, and Gurbaaz Gill
