import axios from 'axios';

const googleApi = axios.create();
googleApi.defaults.headers.common = {};

const apiKey = "&key=AIzaSyD7CXjdwbiWpjFADwOGsX0XZoJPXUgej7c";

// base URL gets 4 books
const baseURL = "https://www.googleapis.com/books/v1/volumes?maxResults=4&q=";

// Searching
    // https://www.googleapis.com/books/v1/volumes?q={{}}&key=AIzaSyD7CXjdwbiWpjFADwOGsX0XZoJPXUgej7c
    // replace the {{}} with a search term, i.e. title or author

export default{

getByAuthor(input){
    return googleApi.get(baseURL + `inauthor:${input}` + apiKey);
},

getBySeries(input) {
    return googleApi.get(baseURL + `intitle:${input}` + apiKey);
},

getByTopic(input) {
    return googleApi.get(baseURL + `${input}` + apiKey);
},

}
