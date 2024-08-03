package com.swiss.sharing.client.service.mapper;

import com.swiss.sharing.client.service.dto.AddressDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class GoogleApiAddressMapperTest {
    GoogleApiAddressMapper googleApiAddressMapper = new GoogleApiAddressMapper();
    String input = """
            {
                "plus_code": {
                    "compound_code": "54VV+HJ7 Geneva, Switzerland",
                    "global_code": "8FR854VV+HJ7"
                },
                "results": [
                    {
                        "address_components": [
                            {
                                "long_name": "4",
                                "short_name": "4",
                                "types": [
                                    "street_number"
                                ]
                            },
                            {
                                "long_name": "Rue Jean-Violette",
                                "short_name": "Rue Jean-Violette",
                                "types": [
                                    "route"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "1205",
                                "short_name": "1205",
                                "types": [
                                    "postal_code"
                                ]
                            }
                        ],
                        "formatted_address": "Rue Jean-Violette 4, 1205 Genève, Switzerland",
                        "geometry": {
                            "location": {
                                "lat": 46.1938859,
                                "lng": 6.1440335
                            },
                            "location_type": "ROOFTOP",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.1952348802915,
                                    "lng": 6.145382480291502
                                },
                                "southwest": {
                                    "lat": 46.1925369197085,
                                    "lng": 6.142684519708498
                                }
                            }
                        },
                        "place_id": "ChIJJS2NDNR6jEcR-81bZe2nlds",
                        "plus_code": {
                            "compound_code": "54VV+HJ Geneva, Switzerland",
                            "global_code": "8FR854VV+HJ"
                        },
                        "types": [
                            "establishment",
                            "point_of_interest"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "4",
                                "short_name": "4",
                                "types": [
                                    "street_number"
                                ]
                            },
                            {
                                "long_name": "Rue Jean-Violette",
                                "short_name": "Rue Jean-Violette",
                                "types": [
                                    "route"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "1205",
                                "short_name": "1205",
                                "types": [
                                    "postal_code"
                                ]
                            }
                        ],
                        "formatted_address": "Rue Jean-Violette 4, 1205 Genève, Switzerland",
                        "geometry": {
                            "location": {
                                "lat": 46.1938646,
                                "lng": 6.1439981
                            },
                            "location_type": "ROOFTOP",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.1952135802915,
                                    "lng": 6.145347080291502
                                },
                                "southwest": {
                                    "lat": 46.1925156197085,
                                    "lng": 6.142649119708498
                                }
                            }
                        },
                        "place_id": "ChIJIWGnDdR6jEcRFYJaFsz-mNc",
                        "plus_code": {
                            "compound_code": "54VV+GH Geneva, Switzerland",
                            "global_code": "8FR854VV+GH"
                        },
                        "types": [
                            "street_address"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "8",
                                "short_name": "8",
                                "types": [
                                    "street_number"
                                ]
                            },
                            {
                                "long_name": "Rue Jean-Violette",
                                "short_name": "Rue Jean-Violette",
                                "types": [
                                    "route"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "1205",
                                "short_name": "1205",
                                "types": [
                                    "postal_code"
                                ]
                            }
                        ],
                        "formatted_address": "Rue Jean-Violette 8, 1205 Genève, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.1939546,
                                    "lng": 6.144587899999999
                                },
                                "southwest": {
                                    "lat": 46.1937801,
                                    "lng": 6.1441843
                                }
                            },
                            "location": {
                                "lat": 46.1938875,
                                "lng": 6.1444924
                            },
                            "location_type": "ROOFTOP",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.1952163302915,
                                    "lng": 6.145735080291502
                                },
                                "southwest": {
                                    "lat": 46.1925183697085,
                                    "lng": 6.143037119708497
                                }
                            }
                        },
                        "place_id": "ChIJPQpj9dN6jEcRHdzPxmc3Az8",
                        "types": [
                            "premise"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "4-12",
                                "short_name": "4-12",
                                "types": [
                                    "street_number"
                                ]
                            },
                            {
                                "long_name": "Rue Jean-Violette",
                                "short_name": "Rue Jean-Violette",
                                "types": [
                                    "route"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "1205",
                                "short_name": "1205",
                                "types": [
                                    "postal_code"
                                ]
                            }
                        ],
                        "formatted_address": "Rue Jean-Violette 4-12, 1205 Genève, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.1940669,
                                    "lng": 6.1447166
                                },
                                "southwest": {
                                    "lat": 46.1939818,
                                    "lng": 6.1436836
                                }
                            },
                            "location": {
                                "lat": 46.1940244,
                                "lng": 6.1442001
                            },
                            "location_type": "GEOMETRIC_CENTER",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.1953733302915,
                                    "lng": 6.145549080291502
                                },
                                "southwest": {
                                    "lat": 46.1926753697085,
                                    "lng": 6.142851119708499
                                }
                            }
                        },
                        "place_id": "ChIJGVewdNR6jEcRUE4siZMfVHc",
                        "types": [
                            "route"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "54VV+HJ",
                                "short_name": "54VV+HJ",
                                "types": [
                                    "plus_code"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "Geneva",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "Geneva",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            }
                        ],
                        "formatted_address": "54VV+HJ Geneva, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.194,
                                    "lng": 6.144125
                                },
                                "southwest": {
                                    "lat": 46.193875,
                                    "lng": 6.144
                                }
                            },
                            "location": {
                                "lat": 46.1939234,
                                "lng": 6.1440496
                            },
                            "location_type": "GEOMETRIC_CENTER",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.1952864802915,
                                    "lng": 6.145411480291503
                                },
                                "southwest": {
                                    "lat": 46.1925885197085,
                                    "lng": 6.142713519708498
                                }
                            }
                        },
                        "place_id": "GhIJ7XZie9IYR0AR7QEEvYGTGEA",
                        "plus_code": {
                            "compound_code": "54VV+HJ Geneva, Switzerland",
                            "global_code": "8FR854VV+HJ"
                        },
                        "types": [
                            "plus_code"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "Saint-François",
                                "short_name": "Saint-François",
                                "types": [
                                    "political",
                                    "sublocality",
                                    "sublocality_level_2"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "1205",
                                "short_name": "1205",
                                "types": [
                                    "postal_code"
                                ]
                            }
                        ],
                        "formatted_address": "Saint-François, 1205 Genève, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.196463,
                                    "lng": 6.149172999999999
                                },
                                "southwest": {
                                    "lat": 46.192553,
                                    "lng": 6.143414
                                }
                            },
                            "location": {
                                "lat": 46.19446260000001,
                                "lng": 6.146688
                            },
                            "location_type": "APPROXIMATE",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.196463,
                                    "lng": 6.149172999999999
                                },
                                "southwest": {
                                    "lat": 46.192553,
                                    "lng": 6.143414
                                }
                            }
                        },
                        "place_id": "ChIJP6VJudN6jEcR8H3OHkAhYYc",
                        "types": [
                            "political",
                            "sublocality",
                            "sublocality_level_2"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "1205",
                                "short_name": "1205",
                                "types": [
                                    "postal_code"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "Geneva",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "Geneva",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            }
                        ],
                        "formatted_address": "1205 Geneva, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.2036772,
                                    "lng": 6.151907599999999
                                },
                                "southwest": {
                                    "lat": 46.18675229999999,
                                    "lng": 6.1200329
                                }
                            },
                            "location": {
                                "lat": 46.1902742,
                                "lng": 6.146484099999999
                            },
                            "location_type": "APPROXIMATE",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.2036772,
                                    "lng": 6.151907599999999
                                },
                                "southwest": {
                                    "lat": 46.18675229999999,
                                    "lng": 6.1200329
                                }
                            }
                        },
                        "place_id": "ChIJ-7T9GFpkjEcRbKHsq3tkHQ4",
                        "types": [
                            "postal_code"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "Centre-Plainpalais-Acacias",
                                "short_name": "Centre-Plainpalais-Acacias",
                                "types": [
                                    "political",
                                    "sublocality",
                                    "sublocality_level_1"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "Genève",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Genève",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            }
                        ],
                        "formatted_address": "Centre-Plainpalais-Acacias, Genève, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.20642000000001,
                                    "lng": 6.156740999999999
                                },
                                "southwest": {
                                    "lat": 46.187179,
                                    "lng": 6.128019
                                }
                            },
                            "location": {
                                "lat": 46.1950184,
                                "lng": 6.1475069
                            },
                            "location_type": "APPROXIMATE",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.20642000000001,
                                    "lng": 6.156740999999999
                                },
                                "southwest": {
                                    "lat": 46.187179,
                                    "lng": 6.128019
                                }
                            }
                        },
                        "place_id": "ChIJGeZKgjFljEcRX88B5F4q-Zc",
                        "types": [
                            "political",
                            "sublocality",
                            "sublocality_level_1"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "Geneva",
                                "short_name": "Geneva",
                                "types": [
                                    "locality",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "Geneva",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            }
                        ],
                        "formatted_address": "Geneva, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.23188589999999,
                                    "lng": 6.1758453
                                },
                                "southwest": {
                                    "lat": 46.1777741,
                                    "lng": 6.1102324
                                }
                            },
                            "location": {
                                "lat": 46.2043907,
                                "lng": 6.1431577
                            },
                            "location_type": "APPROXIMATE",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.23188589999999,
                                    "lng": 6.1758453
                                },
                                "southwest": {
                                    "lat": 46.1777741,
                                    "lng": 6.1102324
                                }
                            }
                        },
                        "place_id": "ChIJ6-LQkwZljEcRObwLezWVtqA",
                        "types": [
                            "locality",
                            "political"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "Geneva",
                                "short_name": "Geneva",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            }
                        ],
                        "formatted_address": "Geneva, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.3645771,
                                    "lng": 6.310219999999999
                                },
                                "southwest": {
                                    "lat": 46.12859050000001,
                                    "lng": 5.9560834
                                }
                            },
                            "location": {
                                "lat": 46.2180073,
                                "lng": 6.121692500000001
                            },
                            "location_type": "APPROXIMATE",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.3645771,
                                    "lng": 6.310219999999999
                                },
                                "southwest": {
                                    "lat": 46.12859050000001,
                                    "lng": 5.9560834
                                }
                            }
                        },
                        "place_id": "ChIJTUlPXENkjEcREYAhCxYV3zY",
                        "types": [
                            "administrative_area_level_2",
                            "political"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "Geneva",
                                "short_name": "GE",
                                "types": [
                                    "administrative_area_level_1",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Geneva",
                                "short_name": "Geneva",
                                "types": [
                                    "administrative_area_level_2",
                                    "political"
                                ]
                            },
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            }
                        ],
                        "formatted_address": "Geneva, Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 46.36457850000001,
                                    "lng": 6.310219999999999
                                },
                                "southwest": {
                                    "lat": 46.12858989999999,
                                    "lng": 5.95608
                                }
                            },
                            "location": {
                                "lat": 46.2180073,
                                "lng": 6.121692500000001
                            },
                            "location_type": "APPROXIMATE",
                            "viewport": {
                                "northeast": {
                                    "lat": 46.36457850000001,
                                    "lng": 6.310219999999999
                                },
                                "southwest": {
                                    "lat": 46.12858989999999,
                                    "lng": 5.95608
                                }
                            }
                        },
                        "place_id": "ChIJ5c1jUUNkjEcRgNgYQIj_AAE",
                        "types": [
                            "administrative_area_level_1",
                            "political"
                        ]
                    },
                    {
                        "address_components": [
                            {
                                "long_name": "Switzerland",
                                "short_name": "CH",
                                "types": [
                                    "country",
                                    "political"
                                ]
                            }
                        ],
                        "formatted_address": "Switzerland",
                        "geometry": {
                            "bounds": {
                                "northeast": {
                                    "lat": 47.8084546,
                                    "lng": 10.4923401
                                },
                                "southwest": {
                                    "lat": 45.81792,
                                    "lng": 5.95608
                                }
                            },
                            "location": {
                                "lat": 46.818188,
                                "lng": 8.227511999999999
                            },
                            "location_type": "APPROXIMATE",
                            "viewport": {
                                "northeast": {
                                    "lat": 47.8084546,
                                    "lng": 10.4923401
                                },
                                "southwest": {
                                    "lat": 45.81792,
                                    "lng": 5.95608
                                }
                            }
                        },
                        "place_id": "ChIJYW1Zb-9kjEcRFXvLDxG1Vlw",
                        "types": [
                            "country",
                            "political"
                        ]
                    }
                ],
                "status": "OK"
            }
                    """;

    @Test
    void fromGoogleJson() {
        var address = googleApiAddressMapper.fromGoogleJson(input, new AddressDTO());
        assertThat(address.getAddress()).isEqualTo("Rue Jean-Violette 4, 1205 Genève, Switzerland");

        assertThat(address.getPostalCode()).isEqualTo("1205");
        assertThat(address.getCity()).isEqualTo("Genève");
        assertThat(address.getCountry()).isEqualTo("Switzerland");
        assertThat(address.getBuildingNumber()).isEqualTo("4");
    }

}