import {alpha_num, alpha_dash, required} from "vee-validate/dist/rules";
import { extend } from "vee-validate";

extend("required", {
    ...required,
    message: "This field is required"
});

extend("minmaxinput", {
    validate(value, {min, max}){
        return value.length > min && value.length < max;
    },
    params:['min','max'],
    message: '{_field_}는 5자에서 15자사이입니다.'
})
extend('alpha_num', {
    ...alpha_num,
    message: '영문과 숫자, 대쉬로만 이루어져야 합니다.'
})
// extend('alpha_dash_underbar', {
//     alpha_dash,
//     message: '영문과 숫자, 대쉬로만 이루어져야 합니다.'
// })

