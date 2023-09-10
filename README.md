# rest-api-java

## Documentation

### Description

This REST API returns the business name, business location and opening hours for all the businesses present in the system.

### Query Pattern

#### GET Request
| Method      | URI | Parameters |
| ----------- | ----------- | ----------- |
| GET      | /places/info       | None     |

#### Input Parameters

Not applicable.

#### Output Parameters

| Value Name      | Data Type | Description | Cardinality |
| ----------- | ----------- | ----------- | ----------- |
| places      | Structure       | A structure with all the places in the system  | 1..1 |
| places:label | String | Name of the place | 1..1 |
| places:location | String | Location of the place | 1..1 | 
| places:days | Structure | Days of the week | 0..7 |
| places:days:day | String | Day of the week | 1..1 |
| places:days:openingHours | Structure | Opening hours for the business | 1..2 |



### API Usage Examples

#### Video Demonstration:

[Video Demonstration](https://youtu.be/QfOeuyxb9Nw)

#### Output Payload:

    {
    "places": [
        {
            "label": "McDonalds",
            "location": "Coina, Barreiro",
            "days": [
                {
                    "day": "Monday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Tuesday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Wednesday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Thursday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Friday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Saturday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                }
            ]
        },
        {
            "label": "Burger King",
            "location": "Zurich, Switzerland",
            "days": [
                {
                    "day": "Monday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Tuesday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Wednesday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Thursday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Friday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                },
                {
                    "day": "Saturday",
                    "openingHours": [
                        {
                            "start": "09:00",
                            "end": "11:00"
                        },
                        {
                            "start": "12:30",
                            "end": "21:00"
                        }
                    ]
                }
            ]
        }
    ]
}
