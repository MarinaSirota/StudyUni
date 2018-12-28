
let el = document.getElementsByClassName('menu-item');
for(let i=0; i<el.length; i++) {
    el[i].addEventListener("mouseenter", showSub, false);
    el[i].addEventListener("mouseleave", hideSub, false);
}
function showSub(e) {
    if(this.children.length>1) {
        this.children[1].style.height = "auto";
        this.children[1].style.overflow = "visible";
        this.children[1].style.opacity = "1";
    } else {
        return false;
    }
}
function hideSub(e) {
    if(this.children.length>1) {
        this.children[1].style.height = "0px";
        this.children[1].style.overflow = "hidden";
        this.children[1].style.opacity = "0";
    } else {
        return false;
    }
}
function divDivt() {
let e=document.getElementById('f');
    e.children[1].style.height = "0px";
    e.children[1].style.overflow = "hidden";
    e.children[1].style.opacity = "0";
}
function divDiv() {
    let el=document.getElementById('f');
    //if(!el) {return;} //!
    if(el.style.display==="block"){
        el.style.display="none";

    } else {
        el.style.display="block";
       //el.style.transition="all 10s ease-in" ;

    }

}
function DivOpen() {
    let el=document.getElementById('f');
        el.style.display="block";

}
function DivClose() {
    let el=document.getElementById('f');
        el.style.display="none";

}