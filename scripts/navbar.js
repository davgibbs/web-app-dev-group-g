//dynamic menu bar when screen size is lower than 768px
//a 3 span button appears on the right side and is clickable to show the menu

var burger = document.querySelector('#burger');
var menu = document.querySelector('#menu');
var nav = document.querySelector(nav);

//on click of the 3 span (burger) displays or removes the menu 
burger.onclick = function () {
        if (menu.style.display == 'block') {
                menu.style.display = 'none';
        }
        else {
                menu.style.display = 'block';
        }
}

//it doesn't work properly as when the screen size gets big again after using the "burger" the navbar do not appear again
//needs modification

