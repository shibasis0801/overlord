// This script sees if the environment is bash / powershell and runs the corresponding scripts. 

const { exec } = require("child_process");

function execute(command) {
    exec(command, (error, stdout, stderr) => {
        if (error) {
            console.error(error.message);
            return;
        }
        if (stderr) {
            console.error(stderr);
            return;
        }
        console.log(stdout);
    });
}

function constructCommand() {
    const windows = process.platform === "win32";
    const slash = windows ? "\\" : "/";

    const command = process.argv[2].replace('.', slash);

    return windows ? 
    `powershell -File .\\scripts\\${command}.ps1`:
    `bash ./scripts/${command}.sh`;
}


execute(constructCommand());