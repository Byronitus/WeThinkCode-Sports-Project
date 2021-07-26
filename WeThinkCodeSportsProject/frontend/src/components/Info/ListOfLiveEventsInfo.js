import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import Button from "@material-ui/core/Button";
import Box from '@material-ui/core/Box';
import PropTypes from "prop-types";

class ListOfLiveEvents extends Component{

    state ={check : false};

    setCheck = () => {
        this.setState({check:true});
    }

    //dynamic Styling
    infoStyle = () =>{
        return {
            backgroundColor: (this.props.user.id %2) == 0? '#c8d4f7cc' :'',
            padding:'10px',
            borderBottom: '1px #ccc dotted',
            display:'flex',
            alignItems:'center',
            justifyContent:'space-between',
            justifyItems: 'flex-start',
        }
    }

    render() {
        if (this.state.check){
            return <Redirect push to={{pathname: "/LiveScore",state: {LeagueID : this.props.user.idLeague, EventID : this.props.user.idEvent}}}/>
        }
        const {id} = this.props.user;
        return(
            <div style={this.infoStyle()}>
                <Box textAlign='center'>
                    <Button color="primary" variant="outlined" onClick={this.setCheck}>
                        {this.props.user.strHomeTeam} vs {this.props.user.strAwayTeam}
                    </Button>
                </Box>
            </div>
        );
    }
}

const iconUsername = {
    display: 'flex',
    justifyContent:'space-between',

}

const buttons = {
    display: 'flex'
}
//PropTypes
ListOfLiveEvents.propTypes = {
    user : PropTypes.object.isRequired
}
export  default  ListOfLiveEvents;