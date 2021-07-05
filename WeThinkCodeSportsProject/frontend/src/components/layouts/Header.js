import React from 'react';
import MenuIcon from '@material-ui/icons/Menu';

const header = {
    background:"#09b110",
    color: "#bd0d0d",
    padding:"10px",
    display:"flex",
    alignItems:'center',
    justifyContent:'space-between',

}

function Header(){
    return(
        <header style={header}>
            <MenuIcon/>
            <h2>Sports Database</h2>
            <p></p>
        </header>
    );
}

export default Header;