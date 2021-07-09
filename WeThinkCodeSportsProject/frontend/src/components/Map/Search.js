import React, {Component} from "react";
import PropTypes from "prop-types";
import SearchInfo from "../Info/SearchInfo";

class Search extends Component{



    render() {
        return this.props.SearchList.map((search) =>(
            <SearchInfo user={search} key={search.id} />
        ));
    }
}

Search.propTypes = {
    SearchList:PropTypes.array.isRequired
}

export default Search;