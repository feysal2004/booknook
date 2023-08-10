import axios from 'axios';

const googleApi = axios.create();
googleApi.defaults.headers.common = {};
const apiKey = "&key=AIzaSyD7CXjdwbiWpjFADwOGsX0XZoJPXUgej7c";

const baseURL = "https://www.googleapis.com/books/v1/volumes?q=";

// Searching
    // https://www.googleapis.com/books/v1/volumes?q={{}}&key=AIzaSyD7CXjdwbiWpjFADwOGsX0XZoJPXUgej7c
    // replace the {{}} with a search term, i.e. title or author

export default{

getBookListBySearchQuery(input){
    return googleApi.get(baseURL + `${input}` + apiKey);
}

}


// Possible things we may want from the return:
// Book title, author, date published, book id, description, thumbnail, infoLink