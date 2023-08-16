import axios from 'axios';

const googleApi = axios.create();
googleApi.defaults.headers.common = {};

//const apiKey = "&key=AIzaSyD7CXjdwbiWpjFADwOGsX0XZoJPXUgej7c";

const apiKey = "&key=AIzaSyAStNZHFdvoINzIF34-Vz94K7-BU6BWAVk";

const baseURL = "https://www.googleapis.com/books/v1/volumes?maxResults=20&q=";

// Searching
    // https://www.googleapis.com/books/v1/volumes?q={{}}&key=AIzaSyD7CXjdwbiWpjFADwOGsX0XZoJPXUgej7c
    // replace the {{}} with a search term, i.e. title or author

export default{

getBookListBySearchQuery(input){
    return googleApi.get(baseURL + `${input}` + apiKey);
},

getBookListByTitle(input) {
    return googleApi.get(baseURL + `intitle:` + `${input}` + apiKey);
},

getBookListByAuthor(input) {
    return googleApi.get(baseURL + `inauthor:` + `${input}` + apiKey);
},

getBookListBySubject(input) {
    return googleApi.get(baseURL + `subject:` + `${input}` + apiKey);
}

}

// a,i,t,x,z

// Possible things we may want from the return:
// Book title, author, date published, book id, description, thumbnail, infoLink