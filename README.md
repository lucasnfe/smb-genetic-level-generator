# A Multi-population Genetic Algorithm for Procedural Generation of Levels for Platform Games
This repository contains the source code to reproduce the results of the [GECCO'14](http://www.sigevo.org/gecco-2014/) paper [A Multi-population Genetic Algorithm for Procedural Generation of Levels for Platform Games](https://www.lucasnferreira.com/assets/papers/2014/gecco-mario.pdf).
This paper presents a multi-population genetic algorithm for procedural generation of levels for platform games such as Super Mario Bros. The algorithm evolves four elements of the game during its generations: terrain, enemies, coins and blocks. Each element has its own codification, population and fitness function. At the end of the evolution, the best four elements are combined to construct the level. The method has as input a vector of parameters to configure the characteristics of each element.

## Reproducing the results

To play a game of Mario on a level with content that has been randomly generated: 

```
java dk.itu.mario.engine.Play
```

To play a game of Mario on a level generated by the proposed genetic algorithm: 

```
java dk.itu.mario.engine.PlayCustomized
```

Controls are the arrow keys, a and s. For information about the Mario AI framework (2012), visit the following website:
https://sites.google.com/site/noormario/LevelGeneration/getting-started

## Citing this Work

If you use this method in your research, please cite:

```
@inproceedings{ferreira2014multi,
  title={A multi-population genetic algorithm for procedural generation of levels for platform games},
  author={Ferreira, Lucas and Pereira, Leonardo and Toledo, Claudio},
  booktitle={Proceedings of the Companion Publication of the 2014 Annual Conference on Genetic and Evolutionary Computation},
  pages={45--46},
  year={2014}
}
```
