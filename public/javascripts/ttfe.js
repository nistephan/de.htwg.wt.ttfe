let size = 4

let gameJson = {
    size:4,
    0: {0:0,1:0,2:0,3:0},
    1: {0:1,1:0,2:0,3:0},
    2: {0:1,1:0,2:0,3:0},
    3: {0:1,1:0,2:0,3:0},
};

class Grid {
    constructor(size){
        this.size = size;
        this.cells = [];
    }


    fill(json) {
       for (let x = 0; x < this.size; x++) {
           for (let y = 0; y < this.size; y++) {
               this.cells.push(json[x][y]);
           }
       }
    }
}

let grid = new Grid(gameJson.size)
grid.fill(gameJson)

function fillGrid(grid) {
    $("#scalar").html(grid.cells);
}


$( document ).ready(function() {
    console.log( "Document is ready, filling grid" );
    fillGrid(grid);
}); 