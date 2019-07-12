## Simple Decryption Engine  

README(ja) is [here](./README_JP.md).  

### Outline  

This application decodes a sentence by using dictionary. The sentence is encoded according to certain rules.     

### Premise  

A rule is not immutable. So this application will forget calculated rule after decoding is over.    

+ Encoding rule  
  - Alphabet is converted to peer character (a => d, b => x, m => a, and so)  
  - Upper case character is stay upper case, and has same peer (a => d, then, A => D)  
  - Non-alphabet character is not converted.  
+ Encoded sentence file  
  - Text file (UTF-8/LF)  
  - Component words are encoded, and separated by space character. 
  - It may contain line-feed-Characters or empty lines, Also It may contain Non-alphabet character.  
+ Dictionary file  
  - Text file (ASCII)   
  - It contains words that are uniquely aggregated from plain sentence (= not encoded).  
  - The words line up separated by half space character.  
  - Words that contains non-alphabet-character are excluded.  

### Limitation  

+ None 

### Specification  

+ Command-line Arguments  
  - This application expects to receive two string arguments at booting.  
  - First : Path to encoded sentence file  
  - Second: Path to dictionary file  
+ Input  
  - Application reads specified two files.  
  - If illegal inputs are specified, Application terminates abnormally.
+ Processing  
  - Application analyzes encoding rule of this time, using the contents of encoded sentence file and dictionary file.
  - Application decodes encoded sentence to plain sentence. And upper/lower cases are preserved before and after decoding.
  - Non-alphabet-characters (space, numbers, signs and multi-byte chars or so) are not decoded.
+ Output  
  - Decoded (=plain) sentence will be written to Standard-Output.  
  - If the information isn't enough analyzing out the rule, write circumstance as being.
