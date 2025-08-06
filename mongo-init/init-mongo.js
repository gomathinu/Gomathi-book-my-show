db = db.getSiblingDB('bookmyshow');

print("init mongo js started executing");

db.show.insertMany([
    {
        showId: "1",
        startTime: new Date("2025-07-26T18:00:00Z"),
        seatList: [
            { seatId:"1", seatNumber: "A1", available: false, showId: "1" },
            { seatId:"2", seatNumber: "A2", available: false, showId: "1" },
            { seatId:"3", seatNumber: "B1", available: false, showId: "1" },
            { seatId:"4", seatNumber: "B2", available: false, showId: "1" }
        ],
        movieId: "101",
        cinemaId: "1"
    },
    {
        showId: "2",
        startTime: new Date("2025-07-27T15:30:00Z"),
        seatList: [
            { seatId:"1", seatNumber: "A1", available: false, showId: "2" },
            { seatId:"2", seatNumber: "A2", available: false, showId: "2" },
            { seatId:"3", seatNumber: "B1", available: false, showId: "2" },
            { seatId:"4", seatNumber: "B2", available: false, showId: "2" }
        ],
        movieId: "102",
        cinemaId: "1"
    },
    {
        showId: "3",
        startTime: new Date("2025-07-27T15:30:00Z"),
        seatList: [
            { seatId:"1", seatNumber: "C1", available: false, showId: "3" },
            { seatId:"2", seatNumber: "C2", available: false, showId: "3" },
            { seatId:"3", seatNumber: "D1", available: false, showId: "3" },
            { seatId:"4", seatNumber: "D2", available: false, showId: "3" }
        ],
        movieId: "101",
        cinemaId: "2"
    }
]);

print("Show initial data inserted");

db.movie.insertMany([
    {
        movieId: "101",
        title: "Sita Ramam",
        genre: "Romance",
        language: "Tamil",
        description: "A love story of a army soldier",
        cast: ["Dulquer Salman", "Mrunal Thakur"],
        crew: ["Director","Music Director"],
        releaseAfter: new Date("2023-01-01"),
        page: 0,
        size: 10,
        sortBy: "releaseDate",
        sortDir: "asc",
        posterUrl: "assets/SitaRamam.jpg"
    },
    {
        movieId: "102",
        title: "Baahubali",
        genre: "Action",
        language: "Tamil",
        description: "Story of an immortal king",
        cast: ["Prabhas", "Anushka", "Rana"],
        crew: ["Rajamouli", "Keeravani"],
        releaseAfter: new Date("2015-01-01"),
        page: 0,
        size: 10,
        sortBy: "releaseDate",
        sortDir: "asc",
        posterUrl: "assets/baahubali.jpg"
    }
]);

print("Movie initial data inserted");

db.city.insertMany([
    {
        cityId: "1",
        cityName: "Chennai",
        cinemaList: [
            {
                cinemaId: "1",
                cinemaName: "PVR",
                showList: [
                    {
                        showId: "1",
                        startTime: new Date("2025-07-26T18:00:00Z"),
                        seatList: [
                            { seatId:"1", seatNumber: "A1", available: false, showId: "1" },
                            { seatId:"2", seatNumber: "A2", available: false, showId: "1" },
                            { seatId:"3", seatNumber: "B1", available: false, showId: "1" },
                            { seatId:"4", seatNumber: "B2", available: false, showId: "1" }
                        ],
                        movieId: "101",
                        cinemaId: "1"
                    },
                    {
                        showId: "2",
                        startTime: new Date("2025-07-27T15:30:00Z"),
                        seatList: [
                            { seatId:"1", seatNumber: "A1", available: false, showId: "2" },
                            { seatId:"2", seatNumber: "A2", available: false, showId: "2" },
                            { seatId:"3", seatNumber: "B1", available: false, showId: "2" },
                            { seatId:"4", seatNumber: "B2", available: false, showId: "2" }
                        ],
                        movieId: "102",
                        cinemaId: "1"
                    }
                ],
                cityId:"1"
            }
        ]
    },
    {
        cityId: "2",
        cityName: "Bangalore",
        cinemaList: [
            {
                cinemaId: "2",
                cinemaName: "Luxe",
                showList: [
                    {
                        showId: "3",
                        startTime: new Date("2025-07-27T15:30:00Z"),
                        seatList: [
                            { seatId:"1", seatNumber: "C1", available: false, showId: "3" },
                            { seatId:"2", seatNumber: "C2", available: false, showId: "3" },
                            { seatId:"3", seatNumber: "D1", available: false, showId: "3" },
                            { seatId:"4", seatNumber: "D2", available: false, showId: "3" }
                        ],
                        movieId: "101",
                        cinemaId: "2"
                    }
                ]
            }
        ]
    }
]);

print("City initial data inserted");

db.cinema.insertMany([
    {
        cinemaId: "1",
        cinemaName: "PVR",
        showList: [
            {
                showId: "1",
                startTime: new Date("2025-07-26T18:00:00Z"),
                seatList: [
                    { seatId:"1", seatNumber: "A1", available: false, showId: "1" },
                    { seatId:"2", seatNumber: "A2", available: false, showId: "1" },
                    { seatId:"3", seatNumber: "B1", available: false, showId: "1" },
                    { seatId:"4", seatNumber: "B2", available: false, showId: "1" }
                ],
                movieId: "101",
                cinemaId: "1"
            },
            {
                showId: "2",
                startTime: new Date("2025-07-27T15:30:00Z"),
                seatList: [
                    { seatId:"1", seatNumber: "A1", available: false, showId: "2" },
                    { seatId:"2", seatNumber: "A2", available: false, showId: "2" },
                    { seatId:"3", seatNumber: "B1", available: false, showId: "2" },
                    { seatId:"4", seatNumber: "B2", available: false, showId: "2" }
                ],
                movieId: "102",
                cinemaId: "1"
            }
        ],
        cityId:"1"
    },
    {
        cinemaId: "2",
        cinemaName: "Luxe",
        showList: [
            {
                showId: "3",
                startTime: new Date("2025-07-27T15:30:00Z"),
                seatList: [
                    { seatId:"1", seatNumber: "C1", available: false, showId: "3" },
                    { seatId:"2", seatNumber: "C2", available: false, showId: "3" },
                    { seatId:"3", seatNumber: "D1", available: false, showId: "3" },
                    { seatId:"4", seatNumber: "D2", available: false, showId: "3" }
                ],
                movieId: "101",
                cinemaId: "2"
            }
        ]
    }
]);

print("Cinema initial data inserted");

db.user.insertMany([
    {
        userId: "goms",
        mobile: "9876543210",
        email: "gomathinachiyar.u@gmail.com",
        otp: "123456",
        isOtpVerified: true
    },
    {
        userId: "nachiyar",
        mobile: "9876543211",
        email: "gomathinachiyar@gmail.com",
        otp: "123457",
        isOtpVerified: true
    }
]);

print("User initial data inserted");

db.seat.insertMany([
    { seatId:"1", seatNumber: "A1", available: false, showId: "1" },
    { seatId:"2", seatNumber: "A2", available: false, showId: "1" },
    { seatId:"3", seatNumber: "B1", available: false, showId: "1" },
    { seatId:"4", seatNumber: "B2", available: false, showId: "1" },
    { seatId:"1", seatNumber: "A1", available: false, showId: "2" },
    { seatId:"2", seatNumber: "A2", available: false, showId: "2" },
    { seatId:"3", seatNumber: "B1", available: false, showId: "2" },
    { seatId:"4", seatNumber: "B2", available: false, showId: "2" },
    { seatId:"1", seatNumber: "C1", available: false, showId: "3" },
    { seatId:"2", seatNumber: "C2", available: false, showId: "3" },
    { seatId:"3", seatNumber: "D1", available: false, showId: "3" },
    { seatId:"4", seatNumber: "D2", available: false, showId: "3" }
]);

print("Seat initial data inserted");