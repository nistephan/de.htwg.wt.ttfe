let websocket = new WebSocket("wss://htwg-ttfe.herokuapp.com/websocket");

class Grid {
    constructor(size){
        this.size = size;
        this.cells = [];
    }

    fill(json) {
       for (let index = 0; index < this.size * this.size; index++) {
               //this.cells.push(json[index].value);
               this.cells[index] = (json[index].value);
       }
    }
}

let grid = new Grid(4)

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

function changeColor(){
    for (let i = 0; i < grid.size * grid.size; i++){
        document.getElementById('cell' + i).setAttribute("class", "value" + document.getElementById('cell' + i).innerHTML);
    }
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
            changeColor();
            alert("update");
        }
    });
}

function connectWebSocket() {
    websocket.onopen = function(event) {
        console.log("Connected to Websocket");
    }

    websocket.onclose = function () {
        console.log('Connection with Websocket Closed!');
    };

    websocket.onerror = function (error) {
        console.log('Error in Websocket Occured: ' + error);
    };

    websocket.onmessage = function (e) {
        if (typeof e.data === "string") {
            let json = JSON.parse(e.data);
            let cells = json.cells;
            grid.fill(cells);
            updateGrid(grid, json.score);
        }

    };
}

$( document ).ready(function() {
    console.log( "Document is ready, filling grid" );
    loadJson();
    connectWebSocket();
}); 