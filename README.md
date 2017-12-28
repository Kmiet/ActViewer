# Act Viewer

It is a simple project for an Object-Oriented Programming course at my university. The main purpose of this program is to parse an act (documment), search for the specified element and print it on screen or write down its table of contents.

### Options

```sh
$ main <options> [arguments]

    -h             show help
    -f <file>      show content from chosen file
    -c <chapter>   show content from chosen chapter
    -a <article>   show content from chosen article
    -s <section>   show content from chosen section
    -p <point>     show content from chosen point
    -l <line>      show content from chosen line
    -u <unit>      show content from chosen unit
    -t <table>     show table of contents of specified element

```

#### Examples

To show file conent:
```sh
$ main -f file.txt
```

To show files table of conent:
```sh
$ main -f file.txt -t
```

To find specified element:
```sh
$ main -f uokik.txt -a 4 -S 4 -l c
```

To show unit:
```sh
$ main -f file.txt -u 1
$ main -f file.txt -u I
```

To show multiple elements ( -element firstElementID:lastElementID ):
```sh
$ main -f uokik.txt -a 29 -s 3b -S 2:4
```


### Tech

| Lib. | Ver. |
| ---- | ---- |
| [Commons-Cli] | 1.4 |


[Commons-Cli]: http://commons.apache.org/proper/commons-cli/