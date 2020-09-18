# centene
For these services I created basic two models named Enrollee and Dependent with the One to Many relations.
The Database used for this service is H2 in-memory
Tests cases for the two different services are also included. 
The exception is also handled in the controller level.

The application can be simply run through the maven spring-boot:run command or from the favourite IDE's

following are the api's exposed for the service

{GET /dependent/{id}}
{PUT /dependent/{id}/update}
{GET /dependent}
{POST /dependent/create/{enrolleeId}/enrollee, consumes [application/json]}
{DELETE /dependent/{id}/delete}


{POST /enrollee/create}
{GET /enrollee/{id}}
{DELETE /enrollee/{id}/delete}
{PUT /enrollee/{id}/update}

