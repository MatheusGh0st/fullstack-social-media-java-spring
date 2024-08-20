<template>
  <section class="bg-white rounded-lg pt-6">
    <div
      class="flex flex-col items-center justify-center px-6 py-8 mx-auto h-fit lg:py-0"
    >
      <a
        href="#"
        class="flex items-center mb-6 text-2xl font-semibold text-blue-600"
      >
        <img
          class="w-8 h-8 mr-2"
          src="https://flowbite.s3.amazonaws.com/blocks/marketing-ui/logo.svg"
          alt="logo"
        />
        Nyx Social
      </a>
      <div
        class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700"
      >
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1
            class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white"
          >
            Create an account
          </h1>
          <form class="space-y-4 md:space-y-6" @submit.prevent="registerUser">
            <div>
              <label
                for="name"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >FirstName</label
              >
              <input
                type="name"
                name="firstName"
                id="firstName"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Jonh"
                required=""
                v-model="name"
              />
            </div>

            <div>
              <label
                for="surname"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Suname</label
              >
              <input
                type="surname"
                name="surname"
                id="surname"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Marston"
                required=""
                v-model="surname"
              />
            </div>
            <div>
              <label
                for="email"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Your email</label
              >
              <input
                type="email"
                name="email"
                id="email"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="name@company.com"
                required=""
                v-model="email"
              />
            </div>
            <div>
              <label
                for="city"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >City</label
              >
              <input
                type="city"
                name="city"
                id="city"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Austin Texas"
                required=""
                v-model="city"
              />
            </div>
            <div>
              <label
                for="Description"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Description</label
              >
              <input
                type="description"
                name="description"
                id="description"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Description Yourself..."
                required="false"
                v-model="description"
              />
            </div>
            <div>
              <label
                for="School"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >School</label
              >
              <input
                type="school"
                name="school"
                id="school"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Harvad School"
                required="false"
                v-model="school"
              />
            </div>
            <div>
              <label
                for="work"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Work</label
              >
              <input
                type="work"
                name="work"
                id="work"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Work in..."
                required="false"
                v-model="work"
              />
            </div>
            <div>
              <label
                for="site"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Website</label
              >
              <input
                type="site"
                name="site"
                id="site"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="www.github.com/profile"
                required="false"
                v-model="website"
              />
            </div>

            <div>
              <label
                for="password"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Password</label
              >
              <input
                type="password"
                name="password"
                id="password"
                placeholder="••••••••"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                required=""
                v-model="password"
              />
            </div>
            <div>
              <label
                for="confirm-password"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Confirm password</label
              >
              <input
                type="password"
                name="confirm-password"
                id="confirm-password"
                placeholder="••••••••"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                required=""
                v-model="confirmPassword"
              />
            </div>
            <div class="flex items-start">
              <div class="flex items-center h-5">
                <input
                  id="terms"
                  aria-describedby="terms"
                  type="checkbox"
                  class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-primary-600 dark:ring-offset-gray-800"
                  required=""
                />
              </div>
              <div class="ml-3 text-sm">
                <label
                  for="terms"
                  class="font-light text-gray-500 dark:text-gray-300"
                  >I accept the
                  <a
                    class="font-medium text-primary-600 hover:underline dark:text-primary-500"
                    href="#"
                    >Terms and Conditions</a
                  ></label
                >
              </div>
            </div>
            <button
              type="submit"
              class="w-full text-white bg-blue-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
            >
              Create an account
            </button>
            <p class="text-sm font-light text-gray-500 dark:text-gray-400">
              Already have an account?
              <router-link
                to="/sign-in"
                class="font-medium text-primary-600 hover:underline dark:text-primary-500"
                >Login here</router-link
              >
            </p>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const APP_HOST = process.env.APP_HOST;

const router = useRouter();
const name = ref("");
const surname = ref("");
const email = ref("");
const description = ref("");
const school = ref("");
const work = ref("");
const city = ref("");
const password = ref("");
const confirmPassword = ref("");

const registerUser = async () => {
  try {
    const response = await axios.post(`${APP_HOST}/api/auth/register`, {
      name: name.value,
      surname: surname.value,
      email: email.value,
      description: description.value,
      city: city.value,
      school: school.value,
      work: work.value,
      password: password.value,
    });

    response.status === 201 ? router.push("/sign-in") : "";
  } catch (error) {
    console.log(error);
  }
};
</script>
