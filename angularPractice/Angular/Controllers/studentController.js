mainApp.controller("studentController", function($scope, $http){

    var url = "Data/data.txt";
    $http.get(url).success( function(response) {
       $scope.students = response;
    });

    $scope.student = {
        firstName: "Daniel",
        lastName: "Glynn",
        fees: 500,
        email: "danielglynn@hotmail.com",
        subjects:[
            {name:"English", marks:90},
            {name:"Geography", marks:80},
            {name:"History", marks:70},
            {name:"Math", marks:60},
            {name:"Irish", marks:50}
        ],
        fullName: function() {
            var studentObject;
            studentObject = $scope.student;
            return studentObject.firstName + " " + studentObject.lastName;
        }
    }
    $scope.resetInfo = function(){
        $scope.student = {
            firstName: "Daniel",
            lastName: "Glynn",
            fees: 500,
            email: "danielglynn@hotmail.com",
            subjects:[
                {name:"English", marks:90},
                {name:"Geography", marks:80},
                {name:"History", marks:70},
                {name:"Math", marks:60},
                {name:"Irish", marks:50}
            ],
            fullName: function() {
                var studentObject;
                studentObject = $scope.student;
                return studentObject.firstName + " " + studentObject.lastName;
            }
        }
    }
});