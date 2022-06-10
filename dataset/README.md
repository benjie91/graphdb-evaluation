# Dataset

**Source:** _https://archive.org/details/201309_foursquare_dataset_umn_

### Description

This data set contains 2153471 users, 1143092 venues, 1021970 check-ins, 27098490 social connections, and 2809581
ratings that users assigned to venues; all extracted from the Foursquare application through the public API. All users
information have been anonymized, i.e., users geolocations are also anonymized. Each user is represented by an id, and
GeoSpatial location. The same for venues. The data are contained in five files, users.dat, venues.dat, checkins.dat,
socialgraph.dat, and ratings.dat. More details about the contents and use of all these files follows.

### Content of Files

| File              | Header                                                 | Description                                                                                                                                                                                     |
|-------------------|--------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| users.dat         | id, latitude, longitude                                | Consists of a set of users such that each user has a unique id and a geospatial location (latitude and longitude) that represents the user home town location.                                  |
| venues.dat        | id, latitude, longitude                                | Consists of a set of venues (e.g., restaurants) such that each venue has a unique id and a geospatial location (lattude and longitude).                                                         |
| checkins.dat      | id, user_id, venue_id, latitude, longitude, created_at | Marks the checkins (visits) of users at venues. Each check-in has a unique id as well as the user id and the venue id.                                                                          |
| socialgraph.dat   | first_user_id, second_user_id                          | Contains the social graph edges (connections) that exist between users. Each social connection consits of two users (friends) represented by two unique ids (first_user_id and second_user_id). |
| ratings.dat       | user_id, venue_id, rating                              | Consists of implicit ratings that quantifies how much a user likes a specific venue.                                                                                                            |
