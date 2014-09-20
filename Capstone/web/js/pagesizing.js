var viewportwidth;
var viewportheight;

function resize() {
    // the more standards compliant browsers (mozilla/netscape/opera/IE7) use window.innerWidth and window.innerHeight

    if (typeof window.innerWidth !== 'undefined') {
        viewportwidth = window.innerWidth;
        viewportheight = window.innerHeight;
    }
var view = document.getElementById("view");
var btl = document.getElementById("btl");
var btr = document.getElementById("btr");
var filler = document.getElementById("filler");
var bbl = document.getElementById("bbl");
var bbr = document.getElementById("bbr");

view.style.width = (0.98*viewportwidth)+"px";
view.style.height = (0.97*viewportheight)+"px";

filler.style.width= (0.98*viewportwidth)+"px";
filler.style.height= ((0.78*viewportheight)+"px");

btl.style.width=((0.15*viewportwidth)+"px");
btr.style.width=((0.15*viewportwidth)+"px");
bbl.style.width=((0.15*viewportwidth)+"px");
bbr.style.width=((0.15*viewportwidth)+"px");

btl.style.height=((0.1*viewportheight)+"px");
btr.style.height=((0.1*viewportheight)+"px");
bbl.style.height=((0.1*viewportheight)+"px");
bbr.style.height=((0.1*viewportheight)+"px");
}