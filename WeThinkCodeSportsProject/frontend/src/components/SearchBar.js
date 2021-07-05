// import {PropTypes} from "@material-ui/core";
// import React, {Component} from "react";
// class SearchBar extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             search: ''
//         }
//         this.onSubmit = this.onSubmit.bind(this);
//     }
//
//     onChange = (e) => {
//         this.setState(({[e.target.name]: e.target.value}))
//     }
//
//     onSubmit(e) {
//         e.preventDefault();
//         let SearchString = this.state;
//         this.props.addSearch(SearchString);
//         this.setState(
//             {search: ''}
//         );
//     }
//
//     render() {
//         return (
//             <form onSubmit={this.onSubmit}>
//                 <div style={{display: 'flex'}}>
//                     <input
//                         type="text"
//                         name="search"
//                         placeholder="Name"
//                         style={rightinput}
//                         value={this.state.name}
//                         onChange={this.onChange}
//                     />
//
//                     <input
//                         type="text"
//                         name="surname"
//                         placeholder="Surname"
//                         style={rightInput}
//                         value={this.state.surname}
//                         onChange={this.onChange}
//                     />
//                 </div>
//                 <br/>
//                 <input
//                     type="submit"
//                     value="Submit"
//                     className="btn"
//                 />
//                 <br/>
//             </form>
//         )
//     }
// }
//
//     const rightInput = {
//         flex:'5',
//         padding:'5px',
//         margin:'10px 0px 0px 10px'
//     }
//
// SearchBar.prototype = {
//     addSearch:PropTypes.func.isRequired,
// }
//
// export default SearchBar