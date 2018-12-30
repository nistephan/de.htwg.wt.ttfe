class Grid {
    constructor(size){
        this.size = size;
        this.cells = [];
    }

    fill(json) {
       for (let index = 0; index < this.size * this.size; index++) {
               this.cells.push(json[index].value);
       }
    }
}

function updateGrid(grid, score) {
    for (let index = 0; index < grid.size * grid.size; index++) {
            $("#cell" + index).html(grid.cells[index]);
    }
    $("#score").html(score);
}

function moveOnServer(direction) {
    $.get("/move/"+direction, function(data) {
        console.log("Move on Server");
    });
}

window.onkeyup = function(e) {
   var key = e.keyCode ? e.keyCode : e.which;

   if (key == 37) {
       moveOnServer("L");
   }else if (key == 38) {
       moveOnServer("U");
   }else if (key == 39) {
       moveOnServer("R");
   }else if (key == 40) {
       moveOnServer("D");
   }
}

function loadJson() {
    $.ajax({
        method: "GET",
        url: "/json",
        dataType: "json",

        success: function (result) {
            grid = new Grid(result.size);
            grid.fill(result.cells);
            updateGrid(grid, result.score);
        }
    });
}

$( document ).ready(function() {
    console.log( "Document is ready, filling grid" );
    loadJson();
}); 