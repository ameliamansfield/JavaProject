// This code runs through tweets from April-June 2020 and analyses average sentiment of certain time range. 

If the tweet was in the specified time range, then it would be added to an array either titled “April” or “June”. After that, the average sentiment method was called  and the array was passed through in order to obtain the sentiment. Finally, the April and June average sentiments were printed. This analysis of tweets, which was a popular communication and community app throughout quarantine, shows if people’s attitudes got more negative or positive from April to June. 
In order to accommodate this experiment, the code filters through a file of tweets, including tweet, author and timestamp, and then additionally takes the sentiment of tweets within a specified date range. A keep method formats the timestamp in order to obtain tweets within the specified time range.The code also skips lines that are null.
From the dataset used, results showed that the average sentiment for June was around 0.009483 and for April it was 0.0049085. The data shows that June had a higher average sentiment than April, meaning that there was an increase in positive tweets. People were connecting online over shared activities and trying to stay positive as quarantine persisted, and I would imagine this would reflect into tweets, as was found in this analysis. 


