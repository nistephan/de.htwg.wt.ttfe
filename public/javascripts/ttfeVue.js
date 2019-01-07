let size = 4;

let ttfeHouses = [cells()]

function cells() {
    let ttfeCells = []
    for (let cell = 0; cell < size * size; cell++) {
        ttfeCells[cell] = ({cell: cell, value: "cell" + cell})
    }
    return ttfeCells
}



$(document).ready(function () {

    var ttfeVueMenu = new Vue({
        el: '#ttfe-vue-menu'
    })


    var ttfeGame = new Vue({
        el:'#ttfe-game'
    })

})

Vue.component('ttfe-field', {
    template:`
        <div class="gamecontainer">
            <div class="game">
                <div v-for="house in houses" class="house size4*4">
                    <div v-for="cell in house" class="cell" v-bind:id="cell.value"></div>
                </div>
            </div>
        </div>
    `,
    data: function () {
        return {
            houses: ttfeHouses
        }
    },

})