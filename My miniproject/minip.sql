create database sports_team;
use sports_team;

create table matches(matchid int primary key auto_increment, statium varchar(80), match_date date, opponent varchar(80), own_score int, opp_score int);

create table player(playerid int primary key auto_increment, player_name varchar(80), age int, season_score int, matchid int references matches(matchid));

select * From player;

create table played(score int); #This will store the scores of the player
								#(since we are storing scores of the opponent in "matches"
								# -table.
                                
insert into player(playerid, player_name, age, season_score, matchid) values(1,"Aditya", 19, 1000, 10);
insert into player(playerid, player_name, age, season_score, matchid) values(2,"Rohit", 19, 500, 20);
