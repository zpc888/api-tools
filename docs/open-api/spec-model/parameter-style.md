# Parameter object style examples 
* Assume a parameter named `color` has one of the following values: 
```javascript
string -> "blue"
array  -> ["blue", "black", "brown"]
object -> { "R": 100, "G": 200, "B": 150 }
```
* The following tables shows examples of rendering differences for each value

| style          |explode| empty  | string      | array                              | object |
| -------------- | ----- | ------ | ----------- | ---------------------------------- | ------ |
| matrix         | false | ;color | ;color=blue | ;color=blue,black,brown            | ;color=R,100,G,200,B,150 |
| matrix         | true  | ;color | ;color=blue | ;color=blue;color=black;color=brown| ;R=100;G=200;B=150       |
| label          | false | .      | .blue       | .blue.black.brown                  | .R.100.G.200.B.150       |
| label          | true  | .      | .blue       | .blue.black.brown                  | .R=100.G=200.B=150       |
| form           | false | color= | color=blue  | color=blue,black,brown             | color=R,100,G,200,B,150  |
| form           | true  | color= | color=blue  | color=blue&color=black&color=brown | R=100&G=200&B=150        |
| simple         | false | n/a    | blue        | blue,black,brown                   | R,100,G,200,B,150        |
| simple         | true  | n/a    | blue        | blue,black,brown                   | R=100,G=200,B=150        |
| spaceDelimited | false | n/a    | n/a         | blue%20black%20brown               | R%20100%20G%20200%20B%20150 |
| pipeDelimited  | false | n/a    | n/a         | blue|black|brown                   | R|100|G|200|B|150 |
| deepObject     | true  | n/a    | n/a         | n/a                                | color[R]=100&color[G]=200&color[B]=150 |
