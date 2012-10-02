#About
A graph-drawing package written in Java. Specify labels and data through the API and get back a JPanel with a graph rendered in it.

#Features
- Supports multiple bars for each field (different coloured stacked bars in screenshot).
- Automatically re-sizes when JPanel is re-sized.
- Line plots overlaying bar plot (see red line in screenshot).

#Future development
Time permitting, I would like to re-factor the code and implement these features:
- Graph creation based on file data (e.g. Graph g = new Graph("myFile.csv");)
- Ability to plot scatter graphs and draw lines of best fit.
- Greater control over axis (at the moment can only have vertical bars; should be able to have horizontal ones as well).
