// use a map to store the history input
var historyQueryMap = new Map();

$(document).ready(function () {
    // $('#autocomplete') is to find element by the ID "autocomplete"
    $('#autocomplete').autocomplete({
        minChars: 3,
        // documentation of the lookup function can be found under the "Custom lookup function" section
        lookup: function (query, doneCallback) {
            handleLookup(query, doneCallback)
        },
        onSelect: function(suggestion) {
            handleSelectSuggestion(suggestion)
        },
        // set the groupby name in the response json data field
        groupBy: "category",
        // set delay time
        deferRequestBy: 300,
        // there are some other parameters that you might want to use to satisfy all the requirements
        // TODO: add other parameters, such as mininum characters
    });

    // full text search
    // bind pressing enter key to a hanlder function

    $('#autocomplete').keypress(function(event) {
        // keyCode 13 is the enter key
        if (event.keyCode == 13) {
            // pass the value of the input box to the hanlder function
            handleNormalSearch($('#autocomplete').val());
            return false;
        }
    });

    //full text search when click button
    $('#fulltext').click(function () {
        handleNormalSearch($('#autocomplete').val());
    });
});

/*
 * This function is called by the library when it needs to lookup a query.
 *
 * The parameter query is the query string.
 * The doneCallback is a callback function provided by the library, after you get the
 *   suggestion list from AJAX, you need to call this function to let the library know.
 */
function handleLookup(query, doneCallback) {
    console.log("autocomplete initiated")
    console.log("sending AJAX request to backend Java Servlet")

    if (historyQueryMap.get(query) != null) {
        doneCallback( { suggestions: historyQueryMap.get(query) } );
        console.log("read from cache");
    } else {
        console.log("read from request");
        // sending the HTTP GET request to the Java Servlet endpoint hero-suggestion
        // with the query data
        $.ajax({
            "method": "GET",
            // generate the request url from the query.
            // escape the query string to avoid errors caused by special characters
            "url": "info-suggestion?query=" + escape(query),
            "success": function (responseJson) {
                // pass the data, query, and doneCallback function into the success handler
                handleLookupAjaxSuccess(responseJson, query, doneCallback)
            },
            "error": function (errorData) {
                console.log("lookup ajax error")
                console.log(errorData)
            }
        });
    }
}

/*
 * This function is used to handle the ajax success callback function.
 * It is called by our own code upon the success of the AJAX request
 *
 * data is the JSON data string you get from your Java Servlet
 *
 */
function handleLookupAjaxSuccess(responseJson, query, doneCallback) {
    console.log("lookup ajax successful");

    // parse the string into JSON
    // var jsonData = JSON.parse(data.data);
    console.log(responseJson.data);

    historyQueryMap.set(query, responseJson.data);
    // call the callback function provided by the autocomplete library
    // add "{suggestions: jsonData}" to satisfy the library response format according to
    //   the "Response Format" section in documentation
    doneCallback( { suggestions: responseJson.data } );
}

/*
 * This function is the select suggestion hanlder function.
 * When a suggestion is selected, this function is called by the library.
 *
 * You can redirect to the page you want using the suggestion data.
 */
function handleSelectSuggestion(suggestion) {
    console.log("you select " + suggestion["value"]);
    var url = suggestion["data"]["category"] + "?id=" + suggestion["data"]["id"];
    console.log(url);
    window.location.replace(url);
}

/*
 * do normal full text search if no suggestion is selected
 */
function handleNormalSearch(query) {
    console.log("doing normal search with query: " + query);
    var url = "full-text?query=" + query;
    alert(window.location.href + url);
    window.location.replace(url);
}