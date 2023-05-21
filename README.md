# Word-Counter
This is a simple word counter that uses Bag Of Words model to read multiple files, counts the occurrences of words in each sentence and then stores the result in a Linked HashMap.

## How it works:
1. Initialize an empty Linked HashMap to store the final results.
2. For each file name:
 - Initialize a map to store word counts for each sentence.
- Read the file and convert its contents to lowercase.
- Split the text into words and sentences.
- Iterate through the words:
- If the word is not empty, store it in map with an initial count of 0 for each sentence.
- Iterate through the words again:
- Increment the count for each word in the respective sentence.
- Add the file name and the prepared map to finalMap.
3. Return finalMap.
