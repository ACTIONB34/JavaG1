/*don't execute as a whole, execute per query (please look at the comments).*/

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
    
/*unreserved seats*/
USE moviereservation;

SELECT  
    seat_number
    FROM seats
    WHERE timeslot_id = 1 AND cinema_id = 1 
    AND reservation_id IS NULL;

/*timeslot_id is changeable and cinema_id is changeable
ids need to be pulled from db based on the user's choices
*/

/*insert reservation*/
USE moviereservation;

INSERT INTO reservations 
    (cinema_id, 
    time, 
    customer_name, 
    total_payment, 
    no_of_kid,
    no_of_adult,
    no_of_senior,
    timeslot_id)
VALUES
    (1,'12:00PM','Johnny Test',150,0,1,0,1);
    
/*everything is changable, ids need to be pulled from db based on the user's choices*/
/*date is TIMESTAMP, ID is auto increment*/