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
  - text file (UTF-8/LF)
  - Component words are encoded, and separated by space character. 
  - It may contain line-feed-Characters or empty lines, Also It may contain Non-alphabet character.
+ Dictionary file  
  - text file (ASCII) 
  - 暗号化元の文章（平文）の英単語を網羅したデータ。
  - 単語は半角空白区切りで並べられている。
  - 平文の英単語を 過不足無く 含んでおり、全て小文字に変換されている。

### Limitation  

+ None 

### Specification

+ Command-line Arguments  
  - This application expects to receive two string arguments at booting.  
  - 1つ目= 暗号化文章のテキストファイルへのパス
  - 2つ目= 辞書のテキストファイルへのパス  
+ Input    
  - 暗号化文章、辞書を入力する。  
  - 起動パラメータ不正、ファイルが入力できない、前提に沿わない内容、の場合、異常終了する。
+ Processing  
  - 暗号化文章と辞書の内容を元に、今回の変換ルールを解析する。
  - 暗号化文章の全てのアルファベットを、大文字小文字を維持したまま平文の状態に復号化する。  
  - アルファベット以外の文字（数字や記号、空白等）は変換せず、そのままとする。
+ Output  
  - 平文を標準出力に出力する。  
  - 与えられた情報からは解析できない場合、その旨を結果として出力する。  

