/*cinemas and movies*/
USE moviereservation;

SELECT	
    cinema_id,
    movie_name,
    movie_director,
    movie_rating,
    movie_genre FROM movies, cinemas
    WHERE movies.movie_id = cinemas.movie_id 
    AND movies.status = 1;

/*timeslots*/
USE moviereservation;

SELECT	
	movies.movie_id,
    time_start
    FROM movies, timeslots
    where movies.movie_id = timeslots.movie_id;