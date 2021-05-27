package com.example.m;

public class Trip extends ATrip implements Comparable {
    private String country;
    private String city;
    private String attraction1;
    private String attraction2;
    private String attraction3;
    private String attraction4;

    public Trip(){
        super();
        country="";
        city="";
        attraction1="";
        attraction2="";
        attraction3="";
        attraction4="";
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country=country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city=city;
    }
    public String getAttraction1() {
        return attraction1;
    }
    public void setAttraction1(String attraction1) {
        this.attraction1=attraction1;
    }
    public String getAttraction2() {
        return attraction2;
    }
    public void setAttraction2(String attraction2) {
        this.attraction2=attraction2;
    }
    public String getAttraction3() {
        return attraction3;
    }
    public void setAttraction3(String attraction3) {
        this.attraction3=attraction3;
    }
    public String getAttraction4() {
        return attraction4;
    }
    public void setAttraction4(String attraction4) {
        this.attraction4=attraction4;
    }

    public void calculateCost(){
        if(attraction1.equals("Car")){
            tripPrice+=200;
        }
        if(attraction2.equals("Museum")){
            tripPrice+=50;
        }
        if(attraction3.equals("Casino")){
            tripPrice+=150;
        }
        if(attraction4.equals("Dining")){
            tripPrice+=100;
        }
        String[] countries = new String[]{
                "Antarctica","Australia","Canada","Denmark","Japan","Monaco","New Zealand","Swaziland","Sweden","Switzerland","United Kingdom","United States",//first world 0-11
                "Austria","Belgium","Bulgaria","China","Czech Republic","France","France Metropolitan", "French Guiana",
                        "French Polynesia", "French Southern Territories","Germany","Italy","Luxembourg","Spain",//second world 12-25
                "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", // third world 26+
                "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Azerbaijan", "Bahamas",
                "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
                "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam",
                "Burkina Faso", "Burundi", "Cambodia", "Cameroon",  "Cape Verde", "Cayman Islands",
                "Central African Republic", "Chad", "Chile",  "Christmas Island", "Cocos (Keeling) Islands", "Colombia",
                "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
                "Croatia (Hrvatska)", "Cuba", "Cyprus",   "Djibouti", "Dominica", "Dominican Republic",
                "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
                "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland",   "Gabon", "Gambia", "Georgia",  "Ghana", "Gibraltar",
                "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
                "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
                "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel",  "Jamaica",  "Jordan",
                "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait",
                "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya",
                "Liechtenstein", "Lithuania",  "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar",
                "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
                "Mayotte", "Mexico", "Micronesia, Federated States of", "Republic of Moldova",  "Mongolia", "Montserrat",
                "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
                "New Caledonia",  "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands",
                "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn",
                "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
                "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
                "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
                "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
                "South Georgia and the South Sandwich Islands",  "Sri Lanka", "St. Helena", "St. Pierre and Miquelon",
                "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Syrian Arab Republic",
                "Taiwan, Province of China", "Tajikistan", "United Republic of Tanzania", "Thailand", "Togo", "Tokelau", "Tonga",
                "Trinidad and Tobago", "Tunisia", "Türkiye", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
                "United Arab Emirates",   "United States Minor Outlying Islands", "Uruguay",
                "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
                "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
        for (int i=0;i<countries.length;i++){
            if (countries[i].equals(country)){
                if(i<=11){
                    tripPrice*=2;
                }
                else if (i<=25){
                    tripPrice*=1.5;
                }
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        Trip temp = (Trip) o;
        if( budget==temp.getBudget()&&
            country.equals(temp.getCountry()) &&
            city.equals(temp.getCity()) &&
            attraction1.equals(temp.getAttraction1()) &&
            attraction2.equals(temp.getAttraction2()) &&
            attraction3.equals(temp.getAttraction3()) &&
            attraction4.equals(temp.getAttraction4()) &&
            tripPrice==temp.getTripPrice())
            return 1;
        else return 0;
    }
}
