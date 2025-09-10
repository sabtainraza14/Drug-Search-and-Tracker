**Drug Search \& Tracker (Android)**



**Project Overview**

Drug Search \& Tracker is an Android application built with Java, MVC architecture, Retrofit, and SQLite.  

The app allows users to:

\- Search for drugs via the RxNav API (https://rxnav.nlm.nih.gov/).

\- Display search results in a RecyclerView.

\- View detailed information about a selected drug in a Detail Fragment.

\- Save selected drugs into a local SQLite database.

\- View saved drugs in a list (with swipe-to-delete functionality).



This project was developed as part of an Android assessment and demonstrates integration of API consumption, persistence with SQLite, and clean separation of concerns using MVC.



---

**Requirements Covered**

\- Java + MVC pattern implementation.

\- RxNav API integration using Retrofit.

\- SQLite database for storing user-selected drugs.

\- RecyclerView with custom Adapter for displaying drugs.

\- Fragment navigation (Search → Detail → Back).

\- Back button handling inside Fragment.

\- Persistence layer (DAO + Controller) for clean DB access.

\- OnResume/FragmentResult to refresh lists after saving.



---



**Bonuses Covered**

\- Logging interceptor for API debugging.

\- Toast messages for feedback on Save/Delete.



---



**Technical Details**

\- Language: Java  

\- Architecture: MVC (Model–View–Controller)  

\- Database: SQLite with DAO pattern  

\- API Client: Retrofit + Gson  

\- UI Components: RecyclerView, Fragments, CardView  



---



**Environment**

\- Java version: Java 11

\- Android SDK Target: API 33 (Android 13)  

\- Minimum SDK: API 29 (Android 10 Q)

\- Build Tools: Gradle  



---



